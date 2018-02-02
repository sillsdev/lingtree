/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.controlsfx.dialog.FontSelectorDialogWithColor;
import org.sil.lingtree.MainApp;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.GlossFontInfo;
import org.sil.lingtree.model.LexFontInfo;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NonTerminalFontInfo;
import org.sil.lingtree.model.SubscriptFontInfo;
import org.sil.lingtree.model.SuperscriptFontInfo;
import org.sil.lingtree.service.TreeBuilder;
import org.sil.lingtree.service.TreeDrawer;
import org.sil.lingtree.view.ControllerUtilities;
import org.sil.lingtree.Constants;
import org.sil.lingtree.ApplicationPreferences;
import org.sil.utility.StringUtilities;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class RootLayoutController implements Initializable {

	MainApp mainApp;
	private Locale currentLocale;
	ResourceBundle bundle;
	LingTreeTree ltTree;
	String sDescription;
	ApplicationPreferences applicationPreferences;

	private String sAboutHeader;
	private String sAboutContent;
	private String sFileFilterDescription;
	private String lingTreeFilterDescription;

	private final String kPressedStyle = "buttonpressed";
	private final String kUnPressedStyle = "buttonunpressed";

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
	private ToggleButton toggleButtonSaveAsPng;
	@FXML
	private ToggleButton toggleButtonSaveAsSVG;

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
	private Tooltip tooltipToolbarSaveAsPng;
	@FXML
	private Tooltip tooltipToolbarSaveAsSVG;

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
	private CheckMenuItem menuItemSaveAsPng;
	@FXML
	private CheckMenuItem menuItemSaveAsSVG;

	@FXML
	private TextArea treeDescription;
	@FXML
	private Pane drawingArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = resources;

		createToolbarButtons(bundle);

		toggleButtonUseFlatTree.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarUseFlatTree = new Tooltip(bundle.getString("tooltip.useflattree"));
		toggleButtonUseFlatTree.setTooltip(tooltipToolbarUseFlatTree);

		toggleButtonSaveAsPng.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarSaveAsPng = new Tooltip(bundle.getString("tooltip.saveaspng"));
		toggleButtonSaveAsPng.setTooltip(tooltipToolbarSaveAsPng);

		toggleButtonSaveAsSVG.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarSaveAsSVG = new Tooltip(bundle.getString("tooltip.saveassvg"));
		toggleButtonSaveAsSVG.setTooltip(tooltipToolbarSaveAsSVG);

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
		this.applicationPreferences = mainApp.getApplicationPreferences();
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
		lingTreeFilterDescription = sFileFilterDescription + " ("
				+ Constants.LINGTREE_DATA_FILE_EXTENSIONS + ")";

	}

	public void setTree(LingTreeTree ltTree) {
		this.ltTree = ltTree;
		treeDescription.setText(ltTree.getDescription());
		menuItemUseFlatTree.setSelected(ltTree.isShowFlatView());
		setToggleButtonStyle(menuItemUseFlatTree, toggleButtonUseFlatTree);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		menuItemSaveAsPng.setSelected(ltTree.isSaveAsPng());
		setToggleButtonStyle(menuItemSaveAsPng, toggleButtonSaveAsPng);
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
		menuItemSaveAsSVG.setSelected(ltTree.isSaveAsSVG());
		setToggleButtonStyle(menuItemSaveAsSVG, toggleButtonSaveAsSVG);
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
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
		sDescription = treeDescription.getText();
		ltTree = TreeBuilder.parseAString(sDescription, ltTree);
		mainApp.getBackEndProvider().setLingTree(ltTree);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		ltTree.setDescription(sDescription);
		TreeDrawer drawer = new TreeDrawer(ltTree);
		drawer.draw(drawingArea);
	}

	@FXML
	private void handleMenuUseFlatTree() {
		setToggleButtonStyle(menuItemUseFlatTree, toggleButtonUseFlatTree);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		handleDrawTree();
	}

	@FXML
	private void handleUseFlatTree() {
		menuItemUseFlatTree.setSelected(!menuItemUseFlatTree.isSelected());
		setToggleButtonStyle(menuItemUseFlatTree, toggleButtonUseFlatTree);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		handleDrawTree();
	}

	private void setToggleButtonStyle(CheckMenuItem menuItem, ToggleButton toggleButton) {
		if (menuItem.isSelected()) {
			int i = toggleButton.getStyleClass().indexOf(kUnPressedStyle);
			if (i >= 0) {
				toggleButton.getStyleClass().remove(i);
			}
			toggleButton.getStyleClass().add(kPressedStyle);
		} else {
			int i = toggleButton.getStyleClass().indexOf(kPressedStyle);
			if (i >= 0) {
				toggleButton.getStyleClass().remove(i);
			}
			toggleButton.getStyleClass().add(kUnPressedStyle);
		}
	}

	@FXML
	private void handleMenuSaveAsPng() {
		setToggleButtonStyle(menuItemSaveAsPng, toggleButtonSaveAsPng);
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
	}

	@FXML
	private void handleSaveAsPng() {
		menuItemSaveAsPng.setSelected(!menuItemSaveAsPng.isSelected());
		setToggleButtonStyle(menuItemSaveAsPng, toggleButtonSaveAsPng);
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
	}

	@FXML
	private void handleMenuSaveAsSVG() {
		setToggleButtonStyle(menuItemSaveAsSVG, toggleButtonSaveAsSVG);
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
	}

	@FXML
	private void handleSaveAsSVG() {
		menuItemSaveAsSVG.setSelected(!menuItemSaveAsSVG.isSelected());
		setToggleButtonStyle(menuItemSaveAsSVG, toggleButtonSaveAsSVG);
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
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
	 * Creates an empty tree.
	 */
	@FXML
	public void handleNewTree() {
		String sDirectoryPath = applicationPreferences.getLastOpenedDirectoryPath();
		if (sDirectoryPath == "") {
			// probably creating a new file the first time the program is run;
			// set the directory to the closest we can to a reasonable default
			sDirectoryPath = tryToGetDefaultDirectoryPath();
		}
		applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
		File fileCreated = ControllerUtilities.doFileSaveAs(mainApp, currentLocale, false,
				lingTreeFilterDescription);
		if (fileCreated != null) {
			final String initialDescription = "()";
			ltTree = new LingTreeTree();
			applicationPreferences.getSavedTreeParameters(ltTree);
			ltTree.setDescription(initialDescription);
			updateAllFontInfos();
			this.treeDescription.setText(initialDescription);
			this.menuItemUseFlatTree.setSelected(ltTree.isShowFlatView());
			this.menuItemSaveAsPng.setSelected(ltTree.isSaveAsPng());
			this.menuItemSaveAsSVG.setSelected(ltTree.isSaveAsSVG());
			mainApp.updateStageTitle(fileCreated);
		}
	}

	private void updateAllFontInfos() {
		updateFontInfoValues(GlossFontInfo.getInstance(), ltTree.getGlossFontInfo());
		updateFontInfoValues(LexFontInfo.getInstance(), ltTree.getLexicalFontInfo());
		updateFontInfoValues(NonTerminalFontInfo.getInstance(), ltTree.getNonTerminalFontInfo());
		updateFontInfoValues(SubscriptFontInfo.getInstance(), ltTree.getSubscriptFontInfo());
		updateFontInfoValues(SuperscriptFontInfo.getInstance(), ltTree.getSuperscriptFontInfo());
	}

	private void updateFontInfoValues(FontInfo fiUsedWhenDrawing, FontInfo fiFromTree) {
		fiUsedWhenDrawing.setColor(fiFromTree.getColor());
		fiUsedWhenDrawing.setFontFamily(fiFromTree.getFontFamily());
		fiUsedWhenDrawing.setFontSize(fiFromTree.getFontSize());
		fiUsedWhenDrawing.setFontType(fiFromTree.getFontType());
	}

	/**
	 * Opens a FileChooser to let the user select a language project to load.
	 */
	@FXML
	public void handleOpenTree() {
		doFileOpen(false);
	}

	public void doFileOpen(Boolean fCloseIfCanceled) {
		File file = ControllerUtilities.getFileToOpen(mainApp, lingTreeFilterDescription,
				Constants.LINGTREE_DATA_FILE_EXTENSIONS);
		if (file != null) {
			mainApp.loadTreeData(file);
			String sDirectoryPath = file.getParent();
			applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
			mainApp.updateStageTitle(file);
		} else if (fCloseIfCanceled) {
			// probably first time running and user chose to open a file
			// but then canceled. We quit.
			System.exit(0);
		}
	}

	/**
	 * Saves the file to the language project file that is currently open. If
	 * there is no open file, the "save as" dialog is shown.
	 * @throws IOException
	 */
	@FXML
	public void handleSaveTree() throws IOException {
		File file = mainApp.getTreeDataFile();
		if (file != null) {
			mainApp.saveTreeData(file);
		} else {
			handleSaveTreeAs();
		}

		if (menuItemSaveAsPng.isSelected()) {
			// make sure the tree has been drawn
			handleDrawTree();
			saveTreeAsPng();
		}

		if (menuItemSaveAsSVG.isSelected()) {
			saveTreeAsSVG();
		}
	}

	private void saveTreeAsPng() throws IOException {
		File file = mainApp.getTreeDataFile();
		if (file == null) {
			return;
		}
		String sFilePath = file.getCanonicalPath();
		if (sFilePath.endsWith("." + Constants.LINGTREE_DATA_FILE_EXTENSION)) {
			int len = sFilePath.length();
			sFilePath = sFilePath.substring(0, len - 4);
		}
		WritableImage wim = new WritableImage((int) ltTree.getXSize(),
				(int) (ltTree.getYSize() + 10));
		drawingArea.snapshot(null, wim);
		File filePng = new File(sFilePath + ".png");
		try {
			BufferedImage bi = SwingFXUtils.fromFXImage(wim, null);
			ImageIO.write(bi, "png", filePng);
		} catch (Exception s) {
		}
	}

	private void saveTreeAsSVG() throws IOException {
		File file = mainApp.getTreeDataFile();
		if (file == null) {
			return;
		}
		String sFilePath = file.getCanonicalPath();
		if (sFilePath.endsWith("." + Constants.LINGTREE_DATA_FILE_EXTENSION)) {
			int len = sFilePath.length();
			sFilePath = sFilePath.substring(0, len - 4);
		}
		sDescription = treeDescription.getText();
		ltTree = TreeBuilder.parseAString(sDescription, ltTree);
		mainApp.getBackEndProvider().setLingTree(ltTree);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		ltTree.setDescription(sDescription);
		TreeDrawer drawer = new TreeDrawer(ltTree);
		StringBuilder sb = drawer.drawAsSVG();
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(sFilePath + ".svg"), "UTF-8"));
			try {
			    out.write(sb.toString());
			} finally {
			    out.close();
			}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveTreeAs() {
		ControllerUtilities.doFileSaveAs(mainApp, currentLocale, false, lingTreeFilterDescription);
		// TODO: make sure we know what the new file path is
	}

	/**
	 * Save tree parameters.
	 *
	 * @throws Exception
	 */
	@FXML
	private void handleSaveTreeParameters() throws Exception {
		applicationPreferences.setSavedTreeParameters(ltTree);
	}

	/**
	 * Change interface language.
	 */
	@FXML
	private void handleChangeInterfaceLanguage() {

		Map<String, ResourceBundle> validLocales = new TreeMap<String, ResourceBundle>();
		getListOfValidLocales(validLocales);

		ChoiceDialog<String> dialog = new ChoiceDialog<>(
				currentLocale.getDisplayLanguage(currentLocale), validLocales.keySet());
		dialog.setTitle(bundle.getString("menu.changeinterfacelanguage"));
		dialog.setHeaderText(bundle.getString("dialog.chooseinterfacelanguage"));
		dialog.setContentText(bundle.getString("dialog.chooselanguage"));

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(locale -> mainApp.setLocale(validLocales.get(locale).getLocale()));
	}

	private void getListOfValidLocales(Map<String, ResourceBundle> choices) {
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			ResourceBundle rb = ResourceBundle.getBundle("org.sil.lingtree.resources.LingTree",
					locale);
			if (rb != null) {
				String localeName = rb.getLocale().getDisplayName(currentLocale);
				if (!StringUtilities.isNullOrEmpty(localeName)) {
					choices.putIfAbsent(localeName, rb);
				}
			}
		}
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

	protected String tryToGetDefaultDirectoryPath() {
		String sDirectoryPath = System.getProperty("user.home") + File.separator;
		File dir = new File(sDirectoryPath);
		if (dir.exists()) {
			// See if there is a "Documents" directory as Windows, Linux, and
			// Mac OS X tend to have
			String sDocumentsDirectoryPath = sDirectoryPath + "Documents" + File.separator;
			dir = new File(sDocumentsDirectoryPath);
			if (dir.exists()) {
				// Try and find or make the "My LingTree" subdirectory of
				// Documents
				String sMyLingTreeDirectoryPath = sDocumentsDirectoryPath
						+ Constants.DEFAULT_DIRECTORY_NAME + File.separator;
				dir = new File(sMyLingTreeDirectoryPath);
				if (dir.exists()) {
					sDirectoryPath = sMyLingTreeDirectoryPath;
				} else {
					boolean success = (dir.mkdir());
					if (success) {
						sDirectoryPath = sMyLingTreeDirectoryPath;
					} else {
						sDirectoryPath = sDocumentsDirectoryPath;
					}
				}
			}
		} else { // give up; let user set it
			sDirectoryPath = "";
		}
		return sDirectoryPath;
	}

	@FXML
	public void handleGlossFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getGlossFontInfo());
		ltTree.setGlossFontInfo(fontInfo);
		GlossFontInfo.getInstance().setFont(fontInfo.getFont());
		GlossFontInfo.getInstance().setColor(fontInfo.getColor());
		handleDrawTree();
	}

	@FXML
	public void handleLexicalFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getLexicalFontInfo());
		ltTree.setLexicalFontInfo(fontInfo);
		LexFontInfo.getInstance().setFont(fontInfo.getFont());
		LexFontInfo.getInstance().setColor(fontInfo.getColor());
		handleDrawTree();
	}

	@FXML
	public void handleNonTerminalFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getNonTerminalFontInfo());
		ltTree.setNonTerminalFontInfo(fontInfo);
		NonTerminalFontInfo.getInstance().setFont(fontInfo.getFont());
		NonTerminalFontInfo.getInstance().setColor(fontInfo.getColor());
		handleDrawTree();
	}

	@FXML
	public void handleSubscriptFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getSubscriptFontInfo());
		ltTree.setSubscriptFontInfo(fontInfo);
		SubscriptFontInfo.getInstance().setFont(fontInfo.getFont());
		SubscriptFontInfo.getInstance().setColor(fontInfo.getColor());
		handleDrawTree();
	}

	@FXML
	public void handleSuperscriptFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getSuperscriptFontInfo());
		ltTree.setSuperscriptFontInfo(fontInfo);
		SuperscriptFontInfo.getInstance().setFont(fontInfo.getFont());
		SuperscriptFontInfo.getInstance().setColor(fontInfo.getColor());
		handleDrawTree();
	}

	public FontInfo showFontInfo(Stage stage, FontInfo fontInfo) {
		// TODO: improve the font selector dialog so that one can type the font
		// family name
		// Can we improve the color picker to use color names, too?
		FontSelectorDialogWithColor dlg = new FontSelectorDialogWithColor(fontInfo.getFont(),
				fontInfo.getColor(), bundle);
		dlg.initOwner(stage);
		dlg.showAndWait();
		Font chosenFont = dlg.getResult();
		if (chosenFont != null) {
			fontInfo.setFont(chosenFont);
			Color color = dlg.getColor();
			fontInfo.setColor(color);
		}
		return fontInfo;
	}

	public void handleTreeSpacingParameters() {
		try {
			// Load the fxml file and create a new stage for the popup.
			Stage dialogStage = new Stage();
			String resource = "fxml/TreeSpacingParametersChooser.fxml";
			String title = bundle.getString("spacingdialog.title");
			FXMLLoader loader = ControllerUtilities.getLoader(mainApp, currentLocale, dialogStage,
					resource, title);

			TreeSpacingParametersController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(mainApp);
			controller.setData(ltTree);
			dialogStage.setResizable(false);
			dialogStage.showAndWait();
			handleDrawTree();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleBackgroundAndLineParameters() {
		try {
			// Load the fxml file and create a new stage for the popup.
			Stage dialogStage = new Stage();
			String resource = "fxml/BackgroundAndLineParametersChooser.fxml";
			String title = bundle.getString("backlinedialog.title");
			FXMLLoader loader = ControllerUtilities.getLoader(mainApp, currentLocale, dialogStage,
					resource, title);

			BackgroundAndLineParametersController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(mainApp);
			controller.setData(ltTree);
			dialogStage.setResizable(false);
			dialogStage.showAndWait();
			handleDrawTree();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
