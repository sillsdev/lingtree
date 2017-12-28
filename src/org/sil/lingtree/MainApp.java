package org.sil.lingtree;
	
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.sil.lingtree.view.RootLayoutController;
import org.sil.lingtree.Constants;
import org.sil.lingtree.MainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Locale locale;
	public static String kApplicationTitle = "LingTree";
	private RootLayoutController controller;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
//			applicationPreferences = new ApplicationPreferences(this);
//			locale = new Locale(applicationPreferences.getLastLocaleLanguage());
			locale = new Locale("en");
//
//			languageProject = new LanguageProject();
//			xmlBackEndProvider = new XMLBackEndProvider(languageProject, locale);
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle(kApplicationTitle);
//			this.primaryStage.getIcons().add(getNewMainIconImage());
//			restoreWindowSettings();

			initRootLayout();

//			saveDataPeriodically(Constants.SAVE_DATA_PERIODICITY);

//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("LingTree.css").toExternalForm());
//			Text t = new Text(0.0, 10.0, "Here is some text");
//			System.out.println("t.y=" + t.getY());
//			System.out.println("layout=" + t.getLayoutY());
//			System.out.println("bounds, local, height:" + t.getBoundsInLocal().getHeight());
//			System.out.println("bounds, parent, height:" + t.getBoundsInParent().getHeight());
//			root.getChildren().add(t);
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
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
//			if (getOperatingSystem().equals("Mac OS X")) {
//				adjustMenusForMacOSX();
//			}
//
//			sNotImplementedYetHeader = bundle.getString("misc.niyheader");
//			sNotImplementedYetContent = bundle.getString("misc.niycontent");

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			controller = loader.getController();
			//controller.setMainApp(this, locale, languageProject);
			controller.setMainApp(this);

			// Try to load last opened file.
//			File file = applicationPreferences.getLastOpenedFile();
//			if (file != null && file.exists()) {
//				loadLanguageData(file);
//			} else {
//				boolean fSucceeded = askUserForNewOrToOpenExistingFile(bundle, controller);
//				if (!fSucceeded) {
//					System.exit(0);
//				}
//			}
//
//			updateStatusBarNumberOfItems("");

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("non-IO Exception caught!");
			e.printStackTrace();
		}
	}

}
