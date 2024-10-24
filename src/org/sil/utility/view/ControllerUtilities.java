// Copyright (c) 2017-2020 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.utility.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.controlsfx.control.StatusBar;
import org.sil.utility.service.ValidLocaleCollector;
import org.sil.lingtree.MainApp;
import org.sil.utility.ApplicationPreferencesUtilities;
import org.sil.utility.MainAppUtilities;
import org.sil.utility.StringUtilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class ControllerUtilities {
	public static Tooltip createToolbarButtonWithImage(String sUrl, Button buttonToolbar,
			Tooltip buttonTooltip, String sTooltip, String sLocation) {
		ImageView imageView = new ImageView();
		String sStandardIconURL = getUriOfProgram() + "/resources/images/" + sUrl;
		Image icon = getIconImageFromURL(sStandardIconURL, sLocation);
		imageView.setImage(icon);
		buttonToolbar.setGraphic(imageView);
		buttonTooltip = new Tooltip(sTooltip);
		buttonToolbar.setTooltip(buttonTooltip);
		return buttonTooltip;
	}

	protected static String getUriOfProgram() {
		String uriOfProgram="";
		File jarFile;
		try {
			CodeSource codeSource = MainApp.class.getProtectionDomain().getCodeSource();
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			File parentFile = jarFile.getParentFile();
			if (parentFile.getPath().toLowerCase().contains("eclipse")) {
				// When using the Eclipse IDE we need this
				uriOfProgram = parentFile.toURI().toString();
			}
			else {
				// The installed version needs this
				uriOfProgram = jarFile.getParentFile().getParentFile().toURI().toString();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uriOfProgram;
	}

	public static Image getIconImageFromURL(String sStandardIconURL, String sLocation) {
		Image icon = new Image(sStandardIconURL);
		if (icon.getHeight() == 0) {
			// normal location failed; try this one
			String sSourcePath = sStandardIconURL.substring(0, 5) + sLocation
					+ sStandardIconURL.substring(5);
			icon = new Image(sSourcePath);
		}
		return icon;
	}

	public static void setDateInStatusBar(StatusBar statusBar, ResourceBundle bundle) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE.withLocale(bundle.getLocale());
		statusBar.setText(LocalDate.now().format(formatter));
	}

	public static File getFileToOpen(MainAppUtilities mainApp, String sInitialFileName,
			String sFileChooserFilterDescription, String sFileExtensions) {
		FileChooser fileChooser = initFileChooser(mainApp, sInitialFileName,
				sFileChooserFilterDescription, sFileExtensions);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		return file;
	}

	public static File getFileToOpen(MainAppUtilities mainApp,
			String sFileChooserFilterDescription, String sFileExtensions) {
		return getFileToOpen(mainApp, "", sFileChooserFilterDescription, sFileExtensions);
	}

	public static FileChooser initFileChooser(MainAppUtilities mainApp,
			String sFileChooserFilterDescription, String sFileExtensions) {
		return initFileChooser(mainApp, "", sFileChooserFilterDescription, sFileExtensions);
	}

	public static FileChooser initFileChooser(MainAppUtilities mainApp, String sInitialFileName,
			String sFileChooserFilterDescription, String sFileExtensions) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				sFileChooserFilterDescription, sFileExtensions);
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialFileName(sInitialFileName);

		ApplicationPreferencesUtilities applicationPreferences = mainApp
				.getApplicationPreferences();
		String sDirectoryPath = applicationPreferences.getLastOpenedDirectoryPath();
		if (!StringUtilities.isNullOrEmpty(sDirectoryPath)) {
			File initialDirectory = new File(sDirectoryPath);
			if (initialDirectory.exists() && initialDirectory.isDirectory()) {
				fileChooser.setInitialDirectory(initialDirectory);
			}
		}
		return fileChooser;
	}

	public static File doFileSaveAs(MainAppUtilities mainApp, Locale locale, Boolean fForceSave,
			String appFilterDescription, String sTitle, String sFileExtension,
			String sFileExtensions, String sPropertiesPath) {
		FileChooser fileChooser = ControllerUtilities.initFileChooser(mainApp,
				appFilterDescription, sFileExtensions);

		File file = null;
		if (fForceSave) {
			while (file == null) {
				file = askUserToSaveFile(mainApp, fileChooser, sTitle, sFileExtension);
				if (file == null) {
					ResourceBundle bundle = ResourceBundle.getBundle(sPropertiesPath, locale);
					String sHeader = bundle.getString("file.requiresaveheader");
					String sContent = bundle.getString("file.requiresavecontent");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(sHeader);
					alert.setHeaderText(null);
					alert.setContentText(sContent);
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons().add(mainApp.getNewMainIconImage());
					alert.showAndWait();
				}
			}
		} else {
			file = askUserToSaveFile(mainApp, fileChooser, sTitle, sFileExtension);
		}
		return file;
	}

	public static File askUserToSaveFile(MainAppUtilities mainApp, FileChooser fileChooser,
			String sTitle, String sFileExtension) {
		if (sTitle != null) {
			fileChooser.setTitle(sTitle);
		}
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(sFileExtension)) {
				file = new File(file.getPath() + sFileExtension);
			}
			mainApp.saveFile(file);
			String sDirectoryPath = file.getParent();
			ApplicationPreferencesUtilities applicationPreferences = mainApp
					.getApplicationPreferences();
			applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
			mainApp.updateStageTitle(file);
		}
		return file;
	}

	public static TextInputDialog getTextInputDialog(MainAppUtilities mainApp, String title,
			String contentText, ResourceBundle bundle) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText("");
		dialog.setGraphic(null);
		dialog.setContentText(contentText);
		Button buttonOK = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
		buttonOK.setText(bundle.getString("label.ok"));
		Button buttonCancel = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		buttonCancel.setText(bundle.getString("label.cancel"));
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(mainApp.getNewMainIconImage());
		return dialog;
	}

	public static void formatTimePassed(long timeStart, String sProcessName) {
		long timePassed = System.currentTimeMillis() - timeStart;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(timePassed);
		long timeRemaining = timePassed - (minutes * 60000);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeRemaining);
		long millis = timeRemaining - (seconds * 1000);
		String sResult = sProcessName + " took " + minutes + ":" + seconds + "." + millis;
		System.out.println(sResult);
	}

	public static FXMLLoader getLoader(MainAppUtilities mainApp, Locale locale, Stage dialogStage,
			String title, URL location, String sPropertiesPath) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(location);
		loader.setResources(ResourceBundle.getBundle(sPropertiesPath, locale));

		AnchorPane page = loader.load();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(mainApp.getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		// set the icon
		dialogStage.getIcons().add(mainApp.getNewMainIconImage());
		dialogStage.setTitle(title);
		return loader;
	}

	public static FXMLLoader getLoader(MainAppUtilities mainApp, Locale locale, Stage dialogStage,
			String title, URL location, ResourceBundle bundle) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(location);
		loader.setResources(bundle);

		AnchorPane page = loader.load();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(mainApp.getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		// set the icon
		dialogStage.getIcons().add(mainApp.getNewMainIconImage());
		dialogStage.setTitle(title);
		return loader;
	}

	public static void adjustMenusIfNeeded(String operatingSystem, Pane mainPane) {
		if (operatingSystem.equals("Mac OS X")) {
			   VBox vbox = (VBox) mainPane.getChildren().get(0);
			   MenuBar menuBar = (MenuBar) vbox.getChildren().get(0);
			   menuBar.useSystemMenuBarProperty().set(false);
			   menuBar.useSystemMenuBarProperty().set(true);
			}
	}

	public static Map<String, ResourceBundle> getValidLocales(Locale currentLocale, String resourceLocation) {
		ValidLocaleCollector collector = new ValidLocaleCollector(currentLocale, resourceLocation);
		collector.collectValidLocales();
		Map<String, ResourceBundle> validLocales = collector.getValidLocales();
		return validLocales;
	}

	public static Map<String, ResourceBundle> getValidLocales(Locale currentLocale, ResourceBundle bundle) {
		ValidLocaleCollector collector = new ValidLocaleCollector(currentLocale, bundle.getBaseBundleName());
		collector.collectValidLocales();
		Map<String, ResourceBundle> validLocales = collector.getValidLocales();
		return validLocales;
	}

	public static void showExceptionInErrorDialog(Exception ex, String sTitle, String sHeader, String sContent, String sLabel) {
		// based on https://code.makery.ch/blog/javafx-dialogs-official/
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(sTitle);
		alert.setHeaderText(sHeader);
		alert.setContentText(sContent);

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label(sLabel);

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.getDialogPane().setExpanded(true);

		alert.showAndWait();
	}
}
