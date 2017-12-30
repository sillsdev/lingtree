/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.sil.lingtree.MainApp;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.service.TreeBuilder;
import org.sil.lingtree.service.TreeDrawer;
import org.sil.lingtree.view.ControllerUtilities;
import org.sil.lingtree.Constants;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class RootLayoutController implements Initializable {

	MainApp mainApp;
	private Locale currentLocale;
	ResourceBundle bundle;
	private String sAboutHeader;
	private String sAboutContent;
	private String sFileFilterDescription;

	private final String kFlatPressedStyle = "useflattreepressed";
	private final String kFlatUnPressedStyle = "useflattreeunpressed";

	@FXML
	private Button buttonToolbarFileOpen;
	@FXML
	private Button buttonToolbarFileNew;
	@FXML
	private Button buttonToolbarFileSave;
	@FXML
	private Button buttonToolbarEditCut;
	@FXML
	private Button buttonToolbarEditCopy;
	@FXML
	private Button buttonToolbarEditPaste;
	@FXML
	private Button buttonToolbarDrawTree;
	@FXML
	private Button buttonToolbarUseFlatTree;
	@FXML
	private ToggleButton toggleButtonUseFlatTree;

	@FXML
	private Tooltip tooltipToolbarFileOpen;
	@FXML
	private Tooltip tooltipToolbarFileNew;
	@FXML
	private Tooltip tooltipToolbarFileSave;
	@FXML
	private Tooltip tooltipToolbarEditCut;
	@FXML
	private Tooltip tooltipToolbarEditCopy;
	@FXML
	private Tooltip tooltipToolbarEditPaste;
	@FXML
	private Tooltip tooltipToolbarDrawTree;
	@FXML
	private Tooltip tooltipToolbarUseFlatTree;

	@FXML
	private MenuItem menuItemEditCopy;
	@FXML
	private MenuItem menuItemEditCut;
	@FXML
	private MenuItem menuItemEditPaste;
	@FXML
	private MenuItem menuItemDrawTree;
	@FXML
	private CheckMenuItem menuItemUseFlatTree;

	@FXML
	private TextArea treeDescription;
	@FXML
	private Pane drawingArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = resources;

		createToolbarButtons(bundle);

		toggleButtonUseFlatTree.getStyleClass().add(kFlatUnPressedStyle);
		tooltipToolbarUseFlatTree = new Tooltip(bundle.getString("tooltip.useflattree"));
		toggleButtonUseFlatTree.setTooltip(tooltipToolbarUseFlatTree);

		treeDescription.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.isShiftDown()) {
					switch (event.getCode()) {
					case DIGIT0:
						processRightParenthesis();
						break;
					default:
						break;
					}
				} else {
					switch (event.getCode()) {
					case RIGHT_PARENTHESIS:
						processRightParenthesis();
						break;
					default:
						break;
					}
				}
			}
		});
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				treeDescription.setPromptText(bundle.getString("label.enterdescription"));
				treeDescription.requestFocus();
			}
		});
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	protected void createToolbarButtons(ResourceBundle bundle) {
		ControllerUtilities.createToolbarButtonWithImage("newAction.png", buttonToolbarFileNew,
				tooltipToolbarFileNew, bundle.getString("tooltip.new"));
		ControllerUtilities.createToolbarButtonWithImage("openAction.png", buttonToolbarFileOpen,
				tooltipToolbarFileOpen, bundle.getString("tooltip.open"));
		ControllerUtilities.createToolbarButtonWithImage("saveAction.png", buttonToolbarFileSave,
				tooltipToolbarFileSave, bundle.getString("tooltip.save"));
		ControllerUtilities.createToolbarButtonWithImage("cutAction.png", buttonToolbarEditCut,
				tooltipToolbarEditCut, bundle.getString("tooltip.cut"));
		ControllerUtilities.createToolbarButtonWithImage("copyAction.png", buttonToolbarEditCopy,
				tooltipToolbarEditCopy, bundle.getString("tooltip.copy"));
		ControllerUtilities.createToolbarButtonWithImage("pasteAction.png", buttonToolbarEditPaste,
				tooltipToolbarEditPaste, bundle.getString("tooltip.paste"));
		ControllerUtilities.createToolbarButtonWithImage("drawTree.png", buttonToolbarDrawTree,
				tooltipToolbarDrawTree, bundle.getString("tooltip.drawtree"));
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		sAboutHeader = bundle.getString("about.header");
		Object[] args = { Constants.VERSION_NUMBER };
		MessageFormat msgFormatter = new MessageFormat("");
		msgFormatter.setLocale(currentLocale);
		msgFormatter.applyPattern(bundle.getString("about.content"));
		sAboutContent = msgFormatter.format(args);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(sAboutHeader);
		alert.setHeaderText(null);
		alert.setContentText(sAboutContent);
		Image silLogo = ControllerUtilities
				.getIconImageFromURL("file:resources/images/SILLogo.png");
		alert.setGraphic(new ImageView(silLogo));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(mainApp.getNewMainIconImage());
		alert.showAndWait();
	}

	@FXML
	private void handleCut() {
		// currentApproachController.handleCut();
	}

	@FXML
	private void handleToolBarCut() {
		// currentApproachController.handleToolBarCut();
	}

	@FXML
	private void handleCopy() {
		// currentApproachController.handleCopy();
	}

	@FXML
	private void handleToolBarCopy() {
		// currentApproachController.handleToolBarCopy();
	}

	@FXML
	private void handlePaste() {
		// currentApproachController.handlePaste();
	}

	@FXML
	private void handleToolBarPaste() {
		// currentApproachController.handleToolBarPaste();
	}

	@FXML
	private void handleDrawTree() {
		drawingArea.getChildren().clear();
		String sDescription = treeDescription.getText();
		if (sDescription.length() == 0) {
			sDescription = "(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))";
		}
		LingTreeTree ltTree = TreeBuilder.parseAString(sDescription);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		TreeDrawer drawer = new TreeDrawer(ltTree);
		drawer.draw(drawingArea);
	}

	@FXML
	private void handleMenuUseFlatTree() {
		setToggleButtonStyle();
	}

	@FXML
	private void handleUseFlatTree() {
		menuItemUseFlatTree.setSelected(!menuItemUseFlatTree.isSelected());
		setToggleButtonStyle();
	}

	private void setToggleButtonStyle() {
		if (menuItemUseFlatTree.isSelected()) {
			int i = toggleButtonUseFlatTree.getStyleClass().indexOf(kFlatUnPressedStyle);
			if (i >= 0) {
				toggleButtonUseFlatTree.getStyleClass().remove(i);
			}
			toggleButtonUseFlatTree.getStyleClass().add(kFlatPressedStyle);
		} else {
			int i = toggleButtonUseFlatTree.getStyleClass().indexOf(kFlatPressedStyle);
			if (i >= 0) {
				toggleButtonUseFlatTree.getStyleClass().remove(i);
			}
			toggleButtonUseFlatTree.getStyleClass().add(kFlatUnPressedStyle);
		}
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		// handleSaveProject();
		System.out.println("exiting");
		System.exit(0);
	}

	/**
	 * Creates an empty language project.
	 */
	@FXML
	public void handleNewTree() {
		// TimerService timer = mainApp.getSaveDataPeriodicallyService();
		// if (timer != null) {
		// mainApp.getSaveDataPeriodicallyService().cancel();
		// }
		// String sDirectoryPath =
		// applicationPreferences.getLastOpenedDirectoryPath();
		// if (sDirectoryPath == "") {
		// // probably creating a new file the first time the program is run;
		// // set the directory to the closest we can to a reasonable default
		// sDirectoryPath = tryToGetDefaultDirectoryPath();
		// }
		// applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
		// File fileCreated = ControllerUtilities.doFileSaveAs(mainApp,
		// currentLocale, false,
		// syllableParserFilterDescription);
		// if (fileCreated != null) {
		// File file = new File(Constants.ASHENINKA_STARTER_FILE);
		// mainApp.loadLanguageData(file);
		// mainApp.saveLanguageData(fileCreated);
		// mainApp.updateStageTitle(fileCreated);
		// }
		// if (timer != null) {
		// mainApp.getSaveDataPeriodicallyService().restart();
		// }
	}

	/**
	 * Opens a FileChooser to let the user select a language project to load.
	 */
	@FXML
	public void handleOpenTree() {
		doFileOpen(false);
	}

	public void doFileOpen(Boolean fCloseIfCanceled) {
		// File file = ControllerUtilities.getFileToOpen(mainApp,
		// syllableParserFilterDescription,
		// Constants.LINGTREE_DATA_FILE_EXTENSIONS);
		// if (file != null) {
		// mainApp.loadLanguageData(file);
		// String sDirectoryPath = file.getParent();
		// applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
		// mainApp.updateStageTitle(file);
		// } else if (fCloseIfCanceled) {
		// // probably first time running and user chose to open a file
		// // but then canceled. We quit.
		// System.exit(0);
		// }
	}

	/**
	 * Saves the file to the language project file that is currently open. If
	 * there is no open file, the "save as" dialog is shown.
	 */
	@FXML
	public void handleSaveTree() {
		// File file = mainApp.getLanguageProjectFilePath();
		// if (file != null) {
		// mainApp.saveLanguageData(file);
		// } else {
		// handleSaveProjectAs();
		// }
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveProjectAs() {
		// ControllerUtilities.doFileSaveAs(mainApp, currentLocale, false,
		// syllableParserFilterDescription);
	}

	private void processRightParenthesis() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				int iRightParenthesis = treeDescription.getCaretPosition();
				findMatchingLeftParenthesisAndHighlightIt(iRightParenthesis);
				treeDescription.positionCaret(iRightParenthesis);
				// try {
				// findMatchingLeftParenthesisAndHighlightIt(iRightParenthesis);
				// //Thread thisThread = Thread.currentThread();// .sleep(750);
				// //Thread.sleep(500);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// } finally {
				// }
			}
		});
		// Platform.runLater(new Runnable() {
		// @Override
		// public void run() {
		// try {
		// Thread.currentThread().sleep(750);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });
		// try {
		// Thread.sleep(750);
		// treeDescription.positionCaret(iRightParenthesis);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private void findMatchingLeftParenthesisAndHighlightIt(int iRightParenthesis) {
		int iIndex;
		String sDescription = treeDescription.getText().substring(0, iRightParenthesis - 1);
		int iCloseParen = 0;
		iIndex = iRightParenthesis - 2;
		while (iIndex >= 0) {
			if (sDescription.charAt(iIndex) == ')') {
				iCloseParen++;
			} else if (sDescription.charAt(iIndex) == '(') {
				if (iCloseParen == 0) {
					break;
				} else {
					iCloseParen--;
				}
			}
			iIndex--;
		}
		if (iIndex >= 0) {
			treeDescription.positionCaret(iIndex);
			treeDescription.selectForward();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(MainApp.kApplicationTitle);
			alert.setHeaderText(bundle.getString("error.nomatchingopeningparenthesis"));
			alert.setContentText(bundle.getString("error.missingopenparenthesis"));

			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(mainApp.getNewMainIconImage());

			alert.showAndWait();
		}
		// rtbTreeDescription.Select(Math.max(0, iCurrent - 1), 1);
		// rtbTreeDescription.SelectionFont = m_fntSynTagmeme;
		// rtbTreeDescription.SelectionColor = m_clrSynTagmeme;
		// rtbTreeDescription.Select(iCurrent, 0);
	}
}
