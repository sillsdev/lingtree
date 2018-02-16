// Copyright (c) 2017-2018 SIL International
// This software is licensed under the LGPL, version 2.1 or later
// (http://www.gnu.org/licenses/lgpl-2.1.html)

package org.sil.lingtree;
	
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.sil.lingtree.model.EmptyElementFontInfo;
import org.sil.lingtree.model.GlossFontInfo;
import org.sil.lingtree.model.LexFontInfo;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NonTerminalFontInfo;
import org.sil.lingtree.service.DatabaseMigrator;
import org.sil.lingtree.view.RootLayoutController;
import org.sil.lingtree.Constants;
import org.sil.lingtree.MainApp;
import org.sil.lingtree.view.ControllerUtilities;
import org.sil.lingtree.ApplicationPreferences;
import org.sil.lingtree.backendprovider.XMLBackEndProvider;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class MainApp extends Application {
	private static final String kApplicationIconResource = "file:resources/images/LingTree128x128.png";
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Locale locale;
	public static String kApplicationTitle = "LingTree";
	private RootLayoutController controller;
	private ApplicationPreferences applicationPreferences;
	private XMLBackEndProvider xmlBackEndProvider;
	private LingTreeTree ltTree;
	private final String sOperatingSystem = System.getProperty("os.name");

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ApplicationPreferences getApplicationPreferences() {
		return applicationPreferences;
	}

	public XMLBackEndProvider getBackEndProvider() {
		return xmlBackEndProvider;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			applicationPreferences = new ApplicationPreferences(this);
			locale = new Locale(applicationPreferences.getLastLocaleLanguage());

			ltTree = new LingTreeTree();
			xmlBackEndProvider = new XMLBackEndProvider(ltTree, locale);
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle(kApplicationTitle);
			this.primaryStage.getIcons().add(getNewMainIconImage());
			//this.primaryStage.getScene().getStylesheets().add(getClass().getResource("LingTree.css").toExternalForm());
			restoreWindowSettings();

			initRootLayout();
//			saveDataPeriodically(Constants.SAVE_DATA_PERIODICITY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws IOException {
		applicationPreferences.setLastWindowParameters(ApplicationPreferences.LAST_WINDOW,
				primaryStage);
		applicationPreferences.setLastLocaleLanguage(locale.getLanguage());
		if (controller.isDirty()) {
			controller.askAboutSaving();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/fxml/RootLayout.fxml"));
			ResourceBundle bundle = ResourceBundle.getBundle(Constants.RESOURCE_LOCATION, locale);
			loader.setResources(bundle);
			rootLayout = (BorderPane) loader.load();
			if (getOperatingSystem().equals("Mac OS X")) {
				adjustMenusForMacOSX();
			}

//			sNotImplementedYetHeader = bundle.getString("misc.niyheader");
//			sNotImplementedYetContent = bundle.getString("misc.niycontent");

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			controller = loader.getController();
			controller.setMainApp(this);
			controller.setLocale(locale);

			// Try to load last opened file.
			File file = applicationPreferences.getLastOpenedFile();
			if (file != null && file.exists()) {
				loadTreeData(file);
				controller.setTree(ltTree);
				controller.handleDrawTree();
			} else {
				boolean fSucceeded = false; // askUserForNewOrToOpenExistingFile(bundle, controller);
				if (!fSucceeded) {
					System.exit(0);
				}
			}

//			updateStatusBarNumberOfItems("");

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("non-IO Exception caught!");
			e.printStackTrace();
		}
	}

	public void loadTreeData(File file) {
		DatabaseMigrator migrator = new DatabaseMigrator(file);
		int version = migrator.getVersion();
		if (version < Constants.CURRENT_DATABASE_VERSION) {
			if (version == 1) {
				migrator.setDpi(Screen.getPrimary().getDpi());
			}
			migrator.doMigration();
		}
		xmlBackEndProvider.loadTreeDataFromFile(file);
		ltTree = xmlBackEndProvider.getLingTree();
		setFontsAndColors();
		applicationPreferences.setLastOpenedFilePath(file);
		applicationPreferences.setLastOpenedDirectoryPath(file.getParent());
		updateStageTitle(file);
	}

	private void setFontsAndColors() {
		EmptyElementFontInfo.getInstance().setFont(ltTree.getEmptyElementFontInfo().getFont());
		EmptyElementFontInfo.getInstance().setColor(ltTree.getEmptyElementFontInfo().getColor());
		GlossFontInfo.getInstance().setFont(ltTree.getGlossFontInfo().getFont());
		GlossFontInfo.getInstance().setColor(ltTree.getGlossFontInfo().getColor());
		LexFontInfo.getInstance().setFont(ltTree.getLexicalFontInfo().getFont());
		LexFontInfo.getInstance().setColor(ltTree.getLexicalFontInfo().getColor());
		NonTerminalFontInfo.getInstance().setFont(ltTree.getNonTerminalFontInfo().getFont());
		NonTerminalFontInfo.getInstance().setColor(ltTree.getNonTerminalFontInfo().getColor());
	}



	protected void adjustMenusForMacOSX() {
		VBox vbox = (VBox) rootLayout.getChildren().get(0);
		MenuBar menuBar = (MenuBar) vbox.getChildren().get(0);
		menuBar.useSystemMenuBarProperty().set(true);
	}

	private void restoreWindowSettings() {
		primaryStage = applicationPreferences.getLastWindowParameters(
				ApplicationPreferences.LAST_WINDOW, primaryStage, 660.0, 1000.);
	}

	/**
	 * @return the mainIconImage
	 */
	public Image getNewMainIconImage() {
		Image img = ControllerUtilities.getIconImageFromURL(kApplicationIconResource);
		return img;
	}

	public void saveTreeData(File file) {
		xmlBackEndProvider.saveTreeDataToFile(file);
		applicationPreferences.setLastOpenedFilePath(file);
		applicationPreferences.setLastOpenedDirectoryPath(file.getParent());
	}


	public void updateStageTitle(File file) {
		if (file != null) {
			String sFileNameToUse = file.getName().replace(
					"." + Constants.LINGTREE_DATA_FILE_EXTENSION, "");
			if (controller.isDirty()) {
				sFileNameToUse += "*";
			}
			primaryStage.setTitle(kApplicationTitle + " - " + sFileNameToUse);
		} else {
			primaryStage.setTitle(kApplicationTitle);
		}
	}

	/**
	 * Returns the language project file preference, i.e. the file that was last
	 * opened. The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 *
	 * @return
	 */
	public File getTreeDataFile() {
		String filePath = applicationPreferences.getLastOpenedFilePath();
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}
	public String getOperatingSystem() {
		return sOperatingSystem;
	}

	public void setLocale(Locale locale) {

		this.locale = locale;
		initRootLayout();
	}

	public LingTreeTree getTree() {
		return ltTree;
	}

}
