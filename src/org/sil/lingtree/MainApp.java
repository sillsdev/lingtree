// Copyright (c) 2017-2024 SIL International
// This software is licensed under the LGPL, version 2.1 or later
// (http://www.gnu.org/licenses/lgpl-2.1.html)

package org.sil.lingtree;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.InlineCssTextArea;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.service.BatchTreeHandler;
import org.sil.lingtree.service.DatabaseMigrator;
import org.sil.lingtree.view.RootLayoutController;
import org.sil.lingtree.MainApp;
import org.sil.lingtree.backendprovider.XMLBackEndProvider;
import org.sil.utility.MainAppUtilities;
import org.sil.utility.view.ControllerUtilities;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MainApp extends Application implements MainAppUtilities {
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
	double dividerPosition;
	int dividerChangeCounter = 0;

	static String[] userArgs;

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
			// this.primaryStage.getScene().getStylesheets().add(getClass().getResource("LingTree.css").toExternalForm());
			restoreWindowSettings();

			initRootLayout();
			// saveDataPeriodically(Constants.SAVE_DATA_PERIODICITY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws IOException {
		applicationPreferences.setLastWindowParameters(ApplicationPreferences.LAST_WINDOW,
				primaryStage);
		applicationPreferences.setLastSplitPaneDividerPosition(dividerPosition);
		applicationPreferences.setLastLocaleLanguage(locale.getLanguage());
		if (controller.isDirty()) {
			controller.askAboutSaving();
		}
	}

	public static void main(String[] args) throws IOException {
		userArgs = args;
//		MainApp.showDebugMessage("main:");
//		for (int i = 0; i < args.length; i++) {
//			MainApp.showDebugMessage("\ti='" + args[i] + "'");
//		}
		if (userArgs.length == 0) {
			launch(args);
		} else if (userArgs.length == 1 && !userArgs[0].equals("-b")) {
			launch(args);
		} else if (userArgs[0].equals("-b") && userArgs.length >= 2) {
			processAsBatchFile();
			Platform.exit();
			return;
		} else {
			System.out.println("Usage: -b filename");
			Platform.exit();
			return;
		}
	}

	public static void processAsBatchFile() throws IOException {
		ApplicationPreferences prefs = new ApplicationPreferences(new LingTreeTree());
		Locale locale = new Locale(prefs.getLastLocaleLanguage());
		ResourceBundle bundle = ResourceBundle.getBundle(Constants.RESOURCE_LOCATION, locale);
		String sFilePath = userArgs[1];
		if (!sFilePath.contains(":") && !sFilePath.startsWith("/")) {
			String sWorkingDirectory = System.getProperty("user.dir");
			sFilePath = sWorkingDirectory + File.separator + sFilePath;
		}
		BatchTreeHandler handler = new BatchTreeHandler(sFilePath, bundle);
		handler.processTree(false);
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
			ControllerUtilities.adjustMenusIfNeeded(sOperatingSystem, rootLayout);

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);

			// Because we see the ALT character in the tree description when the
			// tree description node has focus (which it normally does), we need
			// to catch the ALT and put focus on the menu bar. Otherwise, the
			// menu accelerator keys are ignored.
			scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke) {
					if (ke.getCode() == KeyCode.ALT) {
						controller.getMenuBar().requestFocus();
						ke.consume();
					}
				}
			});

			primaryStage.setScene(scene);
			controller = loader.getController();
			controller.setMainApp(this);
			controller.setLocale(locale);

			wrapTreeDescriptionInVirtualizedScrollPane();

			// The splitter can creep up or down between runs so we remember where it
			// originally was and change it only when the user actually moves it.
			// (It tends to change two times during startup.)
			dividerPosition = applicationPreferences.getLastSplitPaneDividerPosition();
			controller.getSplitPane().setDividerPosition(0, dividerPosition);
			Divider divider = controller.getSplitPane().getDividers().get(0);
			divider.positionProperty().addListener((observable, oldValue, newValue) -> {
				if (dividerChangeCounter++ > 1) {
					dividerPosition = newValue.doubleValue();
				}
			});


			File file;
			if (userArgs != null && userArgs.length > 0) {
				// User double-clicked on file name
				// userArgs[0] is the file path
				file = new File(userArgs[0]);
			} else {
				// Try to load last opened file.
				file = applicationPreferences.getLastOpenedFile();
			}
			if (file != null && file.exists()) {
				loadTreeData(file);
				controller.setTree(ltTree);
				controller.handleDrawTree();
			} else {
				boolean fSucceeded = askUserForNewOrToOpenExistingFile(bundle, controller);
				if (!fSucceeded) {
					System.exit(0);
				}
			}

			// updateStatusBarNumberOfItems("");

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("non-IO Exception caught!");
			e.printStackTrace();
		}
	}

	private void wrapTreeDescriptionInVirtualizedScrollPane() {
		// we cannot use FXML for this; we have to do it programmatically
		AnchorPane ap = (AnchorPane) rootLayout.getCenter();
		VBox vb = (VBox) ap.getChildren().get(0);
		SplitPane sp = (SplitPane) vb.getChildren().get(0);
		InlineCssTextArea ta = (InlineCssTextArea) sp.getItems().get(0);
		;
		VirtualizedScrollPane<InlineCssTextArea> vsTreeDescription = new VirtualizedScrollPane<>(ta);
		sp.getItems().set(0, vsTreeDescription);
	}

	protected boolean askUserForNewOrToOpenExistingFile(ResourceBundle bundle,
			RootLayoutController controller) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(bundle.getString("program.name"));
		alert.setHeaderText(bundle.getString("file.initiallynotfound"));
		alert.setContentText(bundle.getString("file.chooseanoption"));
		alert.setResizable(true);
		// Following comes from
		// https://stackoverflow.com/questions/28937392/javafx-alerts-and-their-size
		// It's an attempt to get the buttons' text to show completely
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
				.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(getNewMainIconImage());

		ButtonType buttonCreateNewTree = new ButtonType(bundle.getString("label.createnewtree"),
				ButtonData.OK_DONE);
		ButtonType buttonOpenExistingTree = new ButtonType(
				bundle.getString("label.openexistingtree"));
		ButtonType buttonCancel = new ButtonType(bundle.getString("label.cancel"),
				ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonCreateNewTree, buttonOpenExistingTree, buttonCancel);
		((Button) alert.getDialogPane().lookupButton(buttonCreateNewTree)).setPrefWidth(250);
		((Button) alert.getDialogPane().lookupButton(buttonOpenExistingTree)).setPrefWidth(250);
		((Button) alert.getDialogPane().lookupButton(buttonCancel))
				.setPrefWidth(Region.USE_PREF_SIZE);

		boolean fSucceeded = true;
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == buttonCreateNewTree) {
			controller.handleNewTree();
			if (controller.getTree() == null) {
				// The user canceled creating a new project
				fSucceeded = false;
			}
		} else if (result.get() == buttonOpenExistingTree) {
			File file = controller.doFileOpen(true);
			loadTreeData(file);
			controller.setTree(ltTree);
			controller.styleTreeDescription();
			controller.handleDrawTree();

		} else {
			// ... user chose CANCEL or closed the dialog
			System.exit(0);
		}
		return fSucceeded;
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
		ltTree.setFontsAndColors();
		applicationPreferences.setLastOpenedFilePath(file);
		applicationPreferences.setLastOpenedDirectoryPath(file.getParent());
		updateStageTitle(file);
	}

	private void restoreWindowSettings() {
		primaryStage = applicationPreferences.getLastWindowParameters(
				ApplicationPreferences.LAST_WINDOW, primaryStage, 660.0, 1000.0);
	}

	/**
	 * @return the mainIconImage
	 */
	public Image getNewMainIconImage() {
		Image img = ControllerUtilities.getIconImageFromURL(kApplicationIconResource,
				Constants.RESOURCE_SOURCE_LOCATION, MainApp.class);
		return img;
	}

	/**
	 * Needed by MainAppUtitlities
	 */
	public void saveFile(File file) {
		saveTreeData(file);
	}

	public void saveTreeData(File file) {
		xmlBackEndProvider.saveTreeDataToFile(file);
		applicationPreferences.setLastOpenedFilePath(file);
		applicationPreferences.setLastOpenedDirectoryPath(file.getParent());
	}

	public void updateStageTitle(File file) {
		if (file != null) {
			String sFileNameToUse = file.getName();
			if (controller.isShowFullFilePath()) {
				sFileNameToUse = file.getAbsolutePath();
			}
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
	}

	public LingTreeTree getTree() {
		return ltTree;
	}

	public XMLBackEndProvider getXmlBackEndProvider() {
		return xmlBackEndProvider;
	}

	public static void reportException(Exception ex, ResourceBundle bundle) {
		String sTitle = "Error Found!";
		String sHeader = "A serious error happened.";
		String sContent = "Please copy the exception information below, email it to blackhandrew@gmail.com along with a description of what you were doing.";
		String sLabel = "The exception stacktrace was:";
		if (bundle != null) {
			sTitle = bundle.getString("exception.title");
			sHeader = bundle.getString("exception.header");
			sContent = bundle.getString("exception.content");
			sLabel = bundle.getString("exception.label");
		}
		ControllerUtilities.showExceptionInErrorDialog(ex, sTitle, sHeader, sContent, sLabel);
		System.exit(1);
	}

	public static void showDebugMessage(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setResizable(true);
				alert.setTitle("Debug message");
				alert.setHeaderText("debugging");
				alert.setContentText(msg);
				alert.showAndWait();
			}
		});
	}
}
