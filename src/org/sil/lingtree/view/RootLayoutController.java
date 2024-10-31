/**
 * Copyright (c) 2016-2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
//import org.controlsfx.dialog.FontSelectorDialogWithColor;
import org.fxmisc.richtext.InlineCssTextArea;
import org.sil.lingtree.MainApp;
import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionLexer;
import org.sil.lingtree.model.AbbreviationFontInfo;
import org.sil.lingtree.model.EmptyElementFontInfo;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.GlossFontInfo;
import org.sil.lingtree.model.LexFontInfo;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeType;
import org.sil.lingtree.model.NonTerminalFontInfo;
import org.sil.lingtree.service.GraphicImageSaver;
import org.sil.lingtree.service.NodeTypeDeterminer;
import org.sil.lingtree.service.TreeBuilder;
import org.sil.lingtree.service.TreeDrawer;
import org.sil.lingtree.Constants;
import org.sil.lingtree.ApplicationPreferences;
import org.sil.utility.ClipboardUtilities;
import org.sil.utility.StringUtilities;
import org.sil.utility.service.keyboards.KeyboardChanger;
import org.sil.utility.view.ControllerUtilities;
import org.sil.utility.view.ObservableResourceFactory;
import org.sil.utility.view.FilteringEventDispatcher;
import org.sil.utility.view.FontSelectorDialogWithColor;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class RootLayoutController implements Initializable {

	MainApp mainApp;
	private Locale currentLocale;
	KeyboardChanger keyboardChanger;
	ResourceBundle bundle;
	LingTreeTree ltTree;
	String sDescription;
	ApplicationPreferences applicationPreferences;
	boolean fIsDirty;
	boolean fOpenParenJustTyped = false;
	boolean fCloseParenJustTyped = false;
	boolean fContentJustChangedSoDrawTree = false;

	private List<KeyEvent> itemsKeyedDuringPause = new ArrayList<KeyEvent>();

	private String sAboutHeader;
	private String sAboutContent;
	private String sFileFilterDescription;
	private String lingTreeFilterDescription;
	private String sOperatingSystem;

	private final String kPressedStyle = "buttonpressed";
	private final String kUnPressedStyle = "buttonunpressed";
	private final String kMacOSInstallDirectory = "/Applications/LingTree.app/Contents/app/";

	@FXML
	BorderPane mainPane;
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
	private ToggleButton toggleButtonShowMatchingParenWithArrowKeys;
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
	private Tooltip tooltipToolbarShowMatchingParenWithArrowKeys;
	@FXML
	private Tooltip tooltipToolbarSaveAsPng;
	@FXML
	private Tooltip tooltipToolbarSaveAsSVG;

	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu menuFile;
	@FXML
	private MenuItem menuItemFileNew;
	@FXML
	private MenuItem menuItemFileOpen;
	@FXML
	private MenuItem menuItemFileSaveAs;
	@FXML
	private MenuItem menuItemFileSave;
	@FXML
	private CheckMenuItem menuItemSaveAsPng;
	@FXML
	private CheckMenuItem menuItemSaveAsSVG;
	@FXML
	private MenuItem menuItemFileExit;
	@FXML
	private Menu menuEdit;
	@FXML
	private MenuItem menuItemEditUndo;
	@FXML
	private MenuItem menuItemEditRedo;
	@FXML
	private MenuItem menuItemEditCut;
	@FXML
	private MenuItem menuItemEditCopy;
	@FXML
	private MenuItem menuItemEditPaste;
	@FXML
	private Menu menuTree;
	@FXML
	private MenuItem menuItemDrawTree;
	@FXML
	private Menu menuFormat;
	@FXML
	private CheckMenuItem menuItemUseFlatTree;
	@FXML
	private CheckMenuItem menuItemUseRightToLeftOrientation;
	@FXML
	private MenuItem menuItemNonTerminalFont;
	@FXML
	private MenuItem menuItemLexicalFont;
	@FXML
	private MenuItem menuItemGlossFont;
	@FXML
	private MenuItem menuItemAbbreviationFont;
	@FXML
	private MenuItem menuItemEmptyElementFont;
	@FXML
	private MenuItem menuItemTreeSpacingParameter;
	@FXML
	private MenuItem menuItemBackgroundAndLineParameters;
	@FXML
	private MenuItem menuItemSaveTreeParameters;
	@FXML
	private Menu menuSettings;
	@FXML
	private MenuItem menuItemDescriptionFontSize;
	@FXML
	private CheckMenuItem menuItemDrawAsType;
	@FXML
	private CheckMenuItem menuItemShowMatchingParenWithArrowKeys;
	@FXML
	private MenuItem menuItemShowMatchingParenDelay;
	@FXML
	private CheckMenuItem menuItemShowFullFilePath;
	@FXML
	private MenuItem menuItemKeyboards;
	@FXML
	private MenuItem menuItemChangeInterfaceLanguage;
	@FXML
	private Menu menuHelp;
	@FXML
	private MenuItem menuItemQuickReferenceGuide;
	@FXML
	private MenuItem menuItemUserDocumentation;
	@FXML
	private MenuItem menuItemAbout;
	@FXML
	private Text statusBarKey;

	@FXML
	private InlineCssTextArea treeDescription;
	@FXML
	private Pane drawingArea;
	@FXML
	private SplitPane splitPane;

	protected Clipboard systemClipboard = Clipboard.getSystemClipboard();

	private Font defaultFont;

	// following lines from
	// https://stackoverflow.com/questions/32464974/javafx-change-application-language-on-the-run
	private static final ObservableResourceFactory RESOURCE_FACTORY = ObservableResourceFactory
			.getInstance();
	static {
		RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(Constants.RESOURCE_LOCATION,
				new Locale("en")));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = resources;
		sFileFilterDescription = RESOURCE_FACTORY.getStringBinding("file.filterdescription").get();
		createToolbarButtons(bundle);
		initMenuItemsForLocalization();
		statusBarKey.textProperty().bind(RESOURCE_FACTORY.getStringBinding("label.key"));

		keyboardChanger = KeyboardChanger.getInstance();
		keyboardChanger.initKeyboardHandler();
		treeDescription.caretPositionProperty().addListener((observable, oldValue, newValue) -> {
			String sPrevious = treeDescription.getText(0, newValue);
			NodeType ntype = NodeTypeDeterminer.determineNodeTypeFrom(sPrevious);
			switch (ntype) {
			case NonTerminal:
				keyboardChanger.tryToChangeKeyboardTo(ltTree.getNonTerminalKeyboard());
				break;
			case Lex:
				keyboardChanger.tryToChangeKeyboardTo(ltTree.getLexicalKeyboard());
				break;
			case Gloss:
				keyboardChanger.tryToChangeKeyboardTo(ltTree.getGlossKeyboard());
				break;
			case EmptyElement:
				keyboardChanger.tryToChangeKeyboardTo(ltTree.getEmptyElementKeyboard());
				break;
			case Syntagmeme:
				keyboardChanger.tryToChangeKeyboardTo(ltTree.getSyntagmemeKeyboard());
				break;
			}
		});

		// we use OnKeyTyped because it tells us the character keyed
		// regardless of the keyboard used
		treeDescription.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				fContentJustChangedSoDrawTree = true;
				String sCharacter = event.getCharacter();
				if (!treeDescription.isEditable()) {
					itemsKeyedDuringPause.add(event);
				}
				switch (sCharacter) {
				case "(":
					// the ( is not in the tree description yet. It is after the
					// key is released, so we handle this in onKeyReleased
					// TODO: is there a better way to do this?
					if (treeDescription.isEditable()) {
						fOpenParenJustTyped = true;
					}
					markAsDirty();
					break;

				case ")":
					// The ) is not in the tree description yet. It is after the
					// key is released, so we handle this in onKeyReleased
					// TODO: is there a better way to do this?
					if (treeDescription.isEditable()) {
						fCloseParenJustTyped = true;
					}
					markAsDirty();
					break;
				default:
					if (event.isControlDown()) {
						switch (sCharacter.codePointAt(0)) {
						case 22: // Control-V (paste)
						case 24: // Control-X (cut)
						case 25: // Control-Y (redo)
						case 26: // Control-Z (undo)
							// mark as dirty since they change content
							markAsDirty();
							break;
						default:
							// do not mark as dirty for other control codes
							fContentJustChangedSoDrawTree = false;
							break;
						}
					} else {
						// some other character was typed so consider it dirty
						markAsDirty();
					}
					break;
				}
			}
		});

		// We use OnKeyReleased for arrow keys and to process open and
		// closed parentheses. See above.
		treeDescription.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (!treeDescription.isEditable()) {
					itemsKeyedDuringPause.add(event);
					return;
				}
				int index;
				Image mainIcon = mainApp.getNewMainIconImage();
				if (fCloseParenJustTyped) {
					fCloseParenJustTyped = false;
					TreeDescriptionUIService.setItemsKeyedDuringPause(itemsKeyedDuringPause);
					// we use caret position - 1 because the caret is after
					// the inserted ')'
					TreeDescriptionUIService.processRightParenthesis(treeDescription,
							treeDescription.getCaretPosition() - 1, true,
							applicationPreferences.getShowMatchingParenDelay(), bundle, mainIcon);
				} else if (fOpenParenJustTyped) {
					fOpenParenJustTyped = false;
					insertMatchingClosingParenthesis();
					TreeDescriptionUIService.setItemsKeyedDuringPause(itemsKeyedDuringPause);
					TreeDescriptionUIService.processLeftParenthesis(treeDescription, false,
							applicationPreferences.getShowMatchingParenDelay(), bundle, mainIcon);
				}
				switch (event.getCode()) {
				// ignore these for redisplaying the tree
				case ALT:
				case ALT_GRAPH:
				case CAPS:
				case CONTROL:
				case DOWN:
				case END:
				case ENTER:
				case ESCAPE:
				case F1:
				case F2:
				case F3:
				case F4:
				case F5:
				case F6:
				case F7:
				case F8:
				case F9:
				case F10:
				case F11:
				case F12:
				case HOME:
				case INSERT:
				case KP_DOWN:
				case KP_UP:
				case PAGE_DOWN:
				case PAGE_UP:
				case PRINTSCREEN:
				case SHIFT:
				case UP:
					fContentJustChangedSoDrawTree = false;
					break;
				case LEFT:
				case KP_LEFT:
					fContentJustChangedSoDrawTree = false;
					if (menuItemShowMatchingParenWithArrowKeys.isSelected()) {
						index = treeDescription.getCaretPosition();
						if (treeDescription.getText(index, index + 1).equals(")")) {
							TreeDescriptionUIService
									.setItemsKeyedDuringPause(itemsKeyedDuringPause);
							// we use caret position because the caret is
							// before the ')' we are checking
							TreeDescriptionUIService.processRightParenthesis(treeDescription,
									index, false,
									applicationPreferences.getShowMatchingParenDelay(), bundle,
									mainIcon);
						} else if (treeDescription.getText(index, index + 1).equals("(")) {
							TreeDescriptionUIService
									.setItemsKeyedDuringPause(itemsKeyedDuringPause);
							TreeDescriptionUIService.processLeftParenthesis(treeDescription, false,
									applicationPreferences.getShowMatchingParenDelay(), bundle,
									mainIcon);
						}
					}
					break;
				case KP_RIGHT:
				case RIGHT:
					fContentJustChangedSoDrawTree = false;
					if (menuItemShowMatchingParenWithArrowKeys.isSelected()) {
						index = treeDescription.getCaretPosition();
						if (treeDescription.getText(Math.max(0, index - 1), index).equals("(")) {
							TreeDescriptionUIService
									.setItemsKeyedDuringPause(itemsKeyedDuringPause);
							TreeDescriptionUIService.processLeftParenthesis(treeDescription, true,
									applicationPreferences.getShowMatchingParenDelay(), bundle,
									mainIcon);
						} else if (treeDescription.getText(Math.max(0, index - 1), index).equals(
								")")) {
							TreeDescriptionUIService
									.setItemsKeyedDuringPause(itemsKeyedDuringPause);
							TreeDescriptionUIService.processRightParenthesis(treeDescription,
									index - 1, true,
									applicationPreferences.getShowMatchingParenDelay(), bundle,
									mainIcon);
						}
					}
					break;
				default:
					break;
				}

				// TODO: is this the best place for this?
				computeHighlighting();

				if (menuItemDrawAsType.isSelected() && fContentJustChangedSoDrawTree) {
					handleDrawTree();
				}
			}
		});

		// If we decide we want to show line numbers, we do it like this:
		// treeDescription.setParagraphGraphicFactory(LineNumberFactory.get(treeDescription));

		treeDescription.setWrapText(true);

		// catch control-V for fixing nulls in clipboard
		// This comes from https://stackoverflow.com/questions/61072150/how-to-overwrite-system-default-keyboard-shortcuts-like-ctrlc-ctrlv-by-using
		// accessed on 23 September 2020.
		// It explains that the TextArea catches the control-V event and prevents it from bubbling up to the scene where
		// we have our menu item catch it.  This custom filter blocks the TextArea from seeing the control-V event.
		treeDescription.setEventDispatcher(
		        new FilteringEventDispatcher(treeDescription.getEventDispatcher(), menuItemEditPaste.getAccelerator()));

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				computeHighlighting();
				treeDescription.requestFocus();
				treeDescription.moveTo(0);
			}
		});
	}

	private void initMenuItemsForLocalization() {
		menuFile.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.file"));
		menuItemFileNew.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.new"));
		menuItemFileOpen.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.open"));
		menuItemFileSave.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.save"));
		menuItemFileSaveAs.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.saveas"));
		menuItemSaveAsPng.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.saveaspng"));
		menuItemSaveAsSVG.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.saveassvg"));
		menuItemFileExit.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.exit"));
		menuEdit.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.edit"));
		menuItemEditUndo.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.undo"));
		menuItemEditRedo.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.redo"));
		menuItemEditCut.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.cut"));
		menuItemEditCopy.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.copy"));
		menuItemEditPaste.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.paste"));
		menuTree.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.tree"));
		menuItemDrawTree.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.drawtree"));
		menuFormat.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.format"));
		menuItemUseFlatTree.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.useflattree"));
		menuItemUseRightToLeftOrientation.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.userighttoleftorientation"));
		menuItemNonTerminalFont.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.nonterminalfont"));
		menuItemLexicalFont.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.lexicalfont"));
		menuItemGlossFont.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.glossfont"));
		menuItemAbbreviationFont.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.abbreviationfont"));
		menuItemEmptyElementFont.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.emptyelementfont"));
		menuItemTreeSpacingParameter.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.treespacingparameters"));
		menuItemBackgroundAndLineParameters.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.backgroundandlineparameters"));
		menuItemSaveTreeParameters.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.savetreeparameters"));
		menuSettings.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.settings"));
		menuItemDescriptionFontSize.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.descriptionfontsize"));
		menuItemDrawAsType.textProperty()
				.bind(RESOURCE_FACTORY.getStringBinding("menu.drawastype"));
		menuItemShowMatchingParenWithArrowKeys.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.showmatchingparenwitharrowkeys"));
		menuItemShowMatchingParenDelay.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.showmatchingparendelay"));
		menuItemShowFullFilePath.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.showfullfilepath"));
		menuItemKeyboards.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.keyboards"));
		menuItemChangeInterfaceLanguage.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.changeinterfacelanguage"));
		menuHelp.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.help"));
		menuItemQuickReferenceGuide.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.quickreferenceguide"));
		menuItemUserDocumentation.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("menu.userdocumentation"));
		menuItemAbout.textProperty().bind(RESOURCE_FACTORY.getStringBinding("menu.about"));
	}

	public void computeHighlighting() {
		CharStream input = CharStreams.fromString(treeDescription.getText());
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();

		String syntagmeme = "-fx-font-family: Monospaced;\n-fx-fill: black;\n-fx-font-size:"
				+ applicationPreferences.getTreeDescriptionFontSize() + "pt;";
		String nonterminal = NonTerminalFontInfo.getInstance().getCss();
		String gloss = GlossFontInfo.getInstance().getCss();
		String empty = EmptyElementFontInfo.getInstance().getCss();
		String lexical = LexFontInfo.getInstance().getCss();

		String cssStyleClass = syntagmeme;
		String textClassToUse = NonTerminalFontInfo.getInstance().getCss();
		for (Token token : tokens.getTokens()) {
			// We keep the following output for when we want to see the set of
			// tokens and their types
			// System.out.println("token='" + token.getText() + "'; type=" +
			// token.getType());
			switch (token.getType()) {
			// TODO: if the description grammar changes, we may need to adjust
			// the case values as they may change
			case 1: // opening parenthesis
				cssStyleClass = syntagmeme;
				textClassToUse = nonterminal;
				break;
			case 5: // \L
				cssStyleClass = syntagmeme;
				textClassToUse = lexical;
				break;
			case 6: // \G
				cssStyleClass = syntagmeme;
				textClassToUse = gloss;
				break;
			case 7: // \E
				cssStyleClass = syntagmeme;
				textClassToUse = empty;
				break;
			case 14: // text
			case 15: // text with spaces
				cssStyleClass = textClassToUse;
				break;
			default:
				cssStyleClass = syntagmeme;
				break;
			}
			if (token.getType() != -1) { // -1 is EOF
				int iStart = token.getStartIndex();
				int iStop = token.getStopIndex() + 1;
				treeDescription.setStyle(iStart, iStop, cssStyleClass);
			}
		}
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		keyboardChanger.setStage(mainApp.getPrimaryStage());
		sOperatingSystem = mainApp.getOperatingSystem();
		if (sOperatingSystem.toLowerCase().contains("windows")) {
			menuItemKeyboards.setDisable(false);
		} else {
			menuItemKeyboards.setDisable(true);
		}
		this.applicationPreferences = mainApp.getApplicationPreferences();
		menuItemDrawAsType.setSelected(applicationPreferences.getDrawAsType());
		menuItemShowFullFilePath.setSelected(applicationPreferences.getShowFullFilePath());
		menuItemShowMatchingParenWithArrowKeys.setSelected(applicationPreferences
				.getShowMatchingParenWithArrowKeys());
		toggleButtonShowMatchingParenWithArrowKeys = setToggleButtonStyle(
				menuItemShowMatchingParenWithArrowKeys, toggleButtonShowMatchingParenWithArrowKeys);
		defaultFont = new Font(applicationPreferences.getTreeDescriptionFontSize());
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
		lingTreeFilterDescription = sFileFilterDescription + " ("
				+ Constants.LINGTREE_DATA_FILE_EXTENSIONS + ")";
		RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(Constants.RESOURCE_LOCATION,
				currentLocale));
	}

	public LingTreeTree getTree() {
		return ltTree;
	}

	public void setTree(LingTreeTree ltTree) {
		this.ltTree = ltTree;
		treeDescription.replaceText(ltTree.getDescription());
		menuItemUseFlatTree.setSelected(ltTree.isShowFlatView());
		toggleButtonUseFlatTree = setToggleButtonStyle(menuItemUseFlatTree, toggleButtonUseFlatTree);
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		menuItemSaveAsPng.setSelected(ltTree.isSaveAsPng());
		toggleButtonSaveAsPng = setToggleButtonStyle(menuItemSaveAsPng, toggleButtonSaveAsPng);
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
		menuItemSaveAsSVG.setSelected(ltTree.isSaveAsSVG());
		toggleButtonSaveAsSVG = setToggleButtonStyle(menuItemSaveAsSVG, toggleButtonSaveAsSVG);
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
		menuItemUseRightToLeftOrientation.setSelected(ltTree.isUseRightToLeftOrientation());
		ltTree.setUseRightToLeftOrientation(menuItemUseRightToLeftOrientation.isSelected());
	}

	public SplitPane getSplitPane() {
		return splitPane;
	}

	public List<KeyEvent> getItemsKeyedDuringPause() {
		return itemsKeyedDuringPause;
	}

	public boolean isDirty() {
		return fIsDirty;
	}

	public boolean isShowFullFilePath() {
		return menuItemShowFullFilePath.isSelected();
	}

	private void markAsClean() {
		fIsDirty = false;
		Stage primaryStage = mainApp.getPrimaryStage();
		String sStageTitle = primaryStage.getTitle();
		sStageTitle = sStageTitle.replaceAll("\\*", "");
		primaryStage.setTitle(sStageTitle);
	}

	private void markAsDirty() {
		fIsDirty = true;
		Stage primaryStage = mainApp.getPrimaryStage();
		String sStageTitle = primaryStage.getTitle();
		if (!sStageTitle.endsWith("*")) {
			primaryStage.setTitle(sStageTitle + "*");
		}
	}

	protected void createToolbarButtons(ResourceBundle bundle) {
		tooltipToolbarFileNew = ControllerUtilities.createToolbarButtonWithImage("newAction.png",
				buttonToolbarFileNew, tooltipToolbarFileNew, bundle.getString("tooltip.new"),
				Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarFileNew.textProperty().bind(RESOURCE_FACTORY.getStringBinding("tooltip.new"));
		tooltipToolbarFileOpen = ControllerUtilities.createToolbarButtonWithImage("openAction.png",
				buttonToolbarFileOpen, tooltipToolbarFileOpen, bundle.getString("tooltip.open"),
				Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarFileOpen.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.open"));
		tooltipToolbarFileSave = ControllerUtilities.createToolbarButtonWithImage("saveAction.png",
				buttonToolbarFileSave, tooltipToolbarFileSave, bundle.getString("tooltip.save"),
				Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarFileSave.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.save"));
		tooltipToolbarEditCut = ControllerUtilities.createToolbarButtonWithImage("cutAction.png",
				buttonToolbarEditCut, tooltipToolbarEditCut, bundle.getString("tooltip.cut"),
				Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarEditCut.textProperty().bind(RESOURCE_FACTORY.getStringBinding("tooltip.cut"));
		tooltipToolbarEditCopy = ControllerUtilities.createToolbarButtonWithImage("copyAction.png",
				buttonToolbarEditCopy, tooltipToolbarEditCopy, bundle.getString("tooltip.copy"),
				Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarEditCopy.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.copy"));
		tooltipToolbarEditPaste = ControllerUtilities.createToolbarButtonWithImage(
				"pasteAction.png", buttonToolbarEditPaste, tooltipToolbarEditPaste,
				bundle.getString("tooltip.paste"), Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarEditPaste.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.paste"));
		tooltipToolbarDrawTree = ControllerUtilities.createToolbarButtonWithImage("drawTree.png",
				buttonToolbarDrawTree, tooltipToolbarDrawTree,
				bundle.getString("tooltip.drawtree"), Constants.RESOURCE_SOURCE_LOCATION);
		tooltipToolbarDrawTree.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.drawtree"));

		toggleButtonUseFlatTree.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarUseFlatTree = new Tooltip(bundle.getString("tooltip.useflattree"));
		toggleButtonUseFlatTree.setTooltip(tooltipToolbarUseFlatTree);
		tooltipToolbarUseFlatTree.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.useflattree"));
		toggleButtonUseFlatTree.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("label.useflattree"));

		toggleButtonShowMatchingParenWithArrowKeys.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarShowMatchingParenWithArrowKeys = new Tooltip(RESOURCE_FACTORY
				.getStringBinding("tooltip.showmatchingparenwitharrowkeys").get());
		tooltipToolbarShowMatchingParenWithArrowKeys.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.showmatchingparenwitharrowkeys"));
		toggleButtonShowMatchingParenWithArrowKeys
				.setTooltip(tooltipToolbarShowMatchingParenWithArrowKeys);

		toggleButtonSaveAsPng.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarSaveAsPng = new Tooltip(RESOURCE_FACTORY
				.getStringBinding("tooltip.saveaspng").get());
		tooltipToolbarSaveAsPng.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.saveaspng"));
		toggleButtonSaveAsPng.setTooltip(tooltipToolbarSaveAsPng);

		toggleButtonSaveAsSVG.getStyleClass().add(kUnPressedStyle);
		tooltipToolbarSaveAsSVG = new Tooltip(RESOURCE_FACTORY
				.getStringBinding("tooltip.saveassvg").get());
		tooltipToolbarSaveAsSVG.textProperty().bind(
				RESOURCE_FACTORY.getStringBinding("tooltip.saveassvg"));
		toggleButtonSaveAsSVG.setTooltip(tooltipToolbarSaveAsSVG);

	}

	public void askAboutSaving() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "");
		alert.setTitle(MainApp.kApplicationTitle);
		alert.setHeaderText(RESOURCE_FACTORY.getStringBinding("file.asktosaveheader").get());
		alert.setContentText(RESOURCE_FACTORY.getStringBinding("file.asktosavecontent").get());
		ButtonType buttonYes = new ButtonType(bundle.getString("label.yes"), ButtonData.YES);
		ButtonType buttonNo = new ButtonType(bundle.getString("label.no"), ButtonData.NO);
		alert.getButtonTypes().setAll(buttonYes, buttonNo);

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(mainApp.getNewMainIconImage());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().getButtonData() == ButtonData.YES) {
			try {
				handleDrawTree();
				handleSaveTree();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		sAboutHeader = RESOURCE_FACTORY.getStringBinding("about.header").get();
		Object[] args = { Constants.VERSION_NUMBER };
		MessageFormat msgFormatter = new MessageFormat("");
		msgFormatter.setLocale(currentLocale);
		msgFormatter.applyPattern(RESOURCE_FACTORY.getStringBinding("about.content").get());
		sAboutContent = msgFormatter.format(args);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(sAboutHeader);
		alert.setHeaderText(null);
		alert.setContentText(sAboutContent);
		Image silLogo = ControllerUtilities.getIconImageFromURL(
				"file:resources/images/SILLogo.png", Constants.RESOURCE_SOURCE_LOCATION);
		alert.setGraphic(new ImageView(silLogo));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(mainApp.getNewMainIconImage());
		alert.showAndWait();
	}

	@FXML
	private void handleUserDocumentation() {
		showFileToUser("doc/UserDocumentation.pdf");
	}

	protected void showFileToUser(String sFileToShow) {
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(sFileToShow);
				MainApp.showDebugMessage(myFile.getAbsolutePath());
				String sOS = mainApp.getOperatingSystem().toLowerCase();
				if (sOS.contains("linux")) {
					Runtime.getRuntime().exec(new String[] { "xdg-open", myFile.getAbsolutePath() });
				} else if (sOS.contains("mac")) {
					if (!myFile.exists()) {
						String sFullPath = kMacOSInstallDirectory + sFileToShow;
						System.out
								.println("File '" + sFileToShow + "' does not exist; trying it as '" + sFullPath + "'");
						myFile = new File(sFullPath);
					}
					Desktop.getDesktop().open(myFile);
				} else {
					Desktop.getDesktop().open(myFile);
				}
			} catch (IOException ex) {
				// no application registered for PDFs
				MainApp.reportException(ex, null);
			}
		}
	}


	@FXML
	protected void handleCopy() {
		treeDescription.copy();
		treeDescription.requestFocus();
	}

	@FXML
	protected void handleCut() {
		treeDescription.cut();
		computeHighlighting();
	}

	@FXML
	protected void handlePaste() {
		ClipboardUtilities.removeAnyFinalNullFromStringOnClipboard();
		// now our possibly adjusted string is on the clipboard; do a paste
		treeDescription.paste();
		computeHighlighting();
		handleDrawTree();
		treeDescription.requestFocus();
	}

	@FXML
	protected void handleUndo() {
		treeDescription.undo();
		computeHighlighting();
	}

	@FXML
	protected void handleRedo() {
		treeDescription.redo();
		computeHighlighting();
	}

	@FXML
	public void handleDrawTree() {
		cleanDrawingArea();
		processTreeDrawing();
	}

	private void processTreeDrawing() {
		TreeDrawer drawer = drawTreePrep();
		if (drawer == null) {
			// there was an error in the description; show it
			reportErrorInDescriptionMessage();

		} else {
			drawer.draw(drawingArea);
		}
		treeDescription.requestFocus();
	}

	private void cleanDrawingArea() {
		drawingArea.getChildren().clear();
	}

	private TreeDrawer drawTreePrep() {
		sDescription = treeDescription.getText();
		ltTree = TreeBuilder.parseAString(sDescription, ltTree);
		updateTreeDataToBackEndProvider();
		if (TreeBuilder.getNumberOfErrors() > 0) {
			return null;
		}
		TreeDrawer drawer = new TreeDrawer(ltTree);
		return drawer;
	}

	private void updateTreeDataToBackEndProvider() {
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		ltTree.setUseRightToLeftOrientation(menuItemUseRightToLeftOrientation.isSelected());
		ltTree.setDescription(treeDescription.getText());
		mainApp.getBackEndProvider().setLingTree(ltTree);
	}

	private void reportErrorInDescriptionMessage() {
		String sSyntaxErrorMessage = TreeBuilder.buildErrorMessage(bundle);
		cleanDrawingArea();
		TextFlow textFlow = buildErrorMessageAsTextFlow(sSyntaxErrorMessage);
		drawingArea.getChildren().add(textFlow);
	}

	private TextFlow buildErrorMessageAsTextFlow(String sSyntaxErrorMessage) {
		Text tPart1 = new Text(TreeBuilder.buildErrorMessagePart1(sSyntaxErrorMessage, bundle));
		tPart1.setFill(Color.RED);
		tPart1.setFont(defaultFont);
		Text tPart2 = new Text(TreeBuilder.getDescriptionBeforeMark());
		tPart2.setFill(Color.RED);
		tPart2.setFont(defaultFont);
		Text tPart3 = new Text(" "
				+ RESOURCE_FACTORY.getStringBinding("descriptionsyntaxerror.here").get());
		tPart3.setFill(Color.BLACK);
		tPart3.setStyle("-fx-font-weight:bold;");
		tPart3.setFont(defaultFont);
		Text tPart4 = new Text(TreeBuilder.getDescriptionAfterMark());
		tPart4.setFill(Color.RED);
		tPart4.setFont(defaultFont);
		TextFlow textFlow = new TextFlow(tPart1, tPart2, tPart3, tPart4);
		return textFlow;
	}

	@FXML
	private void handleMenuDescriptionFontSize() {
		final Double[] fontSizes = new Double[] { 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d,
				14d, 15d, 16d, 17d, 18d, 19d, 20d, 21d, 22d, 23d, 24d, 25d, 26d, 27d, 28d, 29d,
				30d, 31d, 32d, 33d, 34d, 35d, 36d, 37d, 38d, 39d, 40d, 41d, 42d, 43d, 44d, 45d,
				46d, 47d, 48d, 49d, 50d, 51d, 52d, 53d, 54d, 55d, 56d, 57d, 58d, 59d, 60d, 61d,
				62d, 63d, 64d, 65d, 66d, 67d, 68d, 69d, 70d, 71d, 72d };
		ChoiceDialog<Double> dialog = new ChoiceDialog<>(12d, fontSizes);
		dialog.setTitle(RESOURCE_FACTORY.getStringBinding("descriptionfontsize.header").get());
		dialog.setHeaderText(RESOURCE_FACTORY.getStringBinding("descriptionfontsize.content").get());
		dialog.setContentText(RESOURCE_FACTORY.getStringBinding("descriptionfontsize.choose").get());
		dialog.setSelectedItem(applicationPreferences.getTreeDescriptionFontSize());
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(mainApp.getNewMainIconImage());
		Optional<Double> result = dialog.showAndWait();
		if (result.isPresent()) {
			defaultFont = new Font(result.get());
			applicationPreferences.setTreeDescriptionFontSize(result.get());
			computeHighlighting();
		}
	}

	@FXML
	private void handleMenuShowMatchingParenDelay() {
		final Double[] fontSizes = new Double[] { 125d, 250d, 375d, 500d, 625d, 750d, 875d, 1000d,
				1125d, 1250d, 1375d, 1500d, 1625d, 1750d, 1875d, 2000d, 2125d, 2250d, 2375d, 2500d,
				2625d, 2750d, 2875d, 3000d, 3125d, 3250d, 3375d, 3500d, 3625d, 3750d, 3875d, 4000d };
		ChoiceDialog<Double> dialog = new ChoiceDialog<>(750d, fontSizes);
		dialog.setTitle(RESOURCE_FACTORY.getStringBinding("showmatchingparendelay.header").get());
		dialog.setHeaderText(RESOURCE_FACTORY.getStringBinding("showmatchingparendelay.content")
				.get());
		dialog.setContentText(RESOURCE_FACTORY.getStringBinding("showmatchingparendelay.choose")
				.get());
		dialog.setSelectedItem(applicationPreferences.getShowMatchingParenDelay());
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(mainApp.getNewMainIconImage());
		Optional<Double> result = dialog.showAndWait();
		if (result.isPresent()) {
			applicationPreferences.setShowMatchingParenDelay(result.get());
		}
	}

	@FXML
	private void handleMenuUseFlatTree() {
		toggleButtonUseFlatTree = setToggleButtonStyle(menuItemUseFlatTree, toggleButtonUseFlatTree);
		processUseFlatTreeChange();
	}

	@FXML
	private void handleUseFlatTree() {
		menuItemUseFlatTree.setSelected(!menuItemUseFlatTree.isSelected());
		toggleButtonUseFlatTree = setToggleButtonStyle(menuItemUseFlatTree, toggleButtonUseFlatTree);
		processUseFlatTreeChange();
	}

	protected void processUseFlatTreeChange() {
		ltTree.setShowFlatView(menuItemUseFlatTree.isSelected());
		handleDrawTree();
		markAsDirty();
		if (ltTree.isShowFlatView() && !ltTree.canUseFlatTree()) {
			String sTitle = RESOURCE_FACTORY.getStringBinding("flattree.title").get();
			String sContent = RESOURCE_FACTORY.getStringBinding("flattree.cannotuse").get();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(sTitle);
			alert.setHeaderText(null);
			alert.setContentText(sContent);
			alert.showAndWait();
		}
		treeDescription.requestFocus();
	}

	@FXML
	private void handleMenuUseRightToLeftOrientation() {
		ltTree.setUseRightToLeftOrientation(menuItemUseRightToLeftOrientation.isSelected());
		handleDrawTree();
		markAsDirty();
	}

	private ToggleButton setToggleButtonStyle(CheckMenuItem menuItem, ToggleButton toggleButton) {
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
		return toggleButton;
	}

	@FXML
	private void handleMenuSaveAsPng() {
		toggleButtonSaveAsPng = setToggleButtonStyle(menuItemSaveAsPng, toggleButtonSaveAsPng);
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
		markAsDirty();
	}

	@FXML
	private void handleSaveAsPng() {
		menuItemSaveAsPng.setSelected(!menuItemSaveAsPng.isSelected());
		toggleButtonSaveAsPng = setToggleButtonStyle(menuItemSaveAsPng, toggleButtonSaveAsPng);
		ltTree.setSaveAsPng(menuItemSaveAsPng.isSelected());
		markAsDirty();
		treeDescription.requestFocus();
	}

	@FXML
	private void handleMenuSaveAsSVG() {
		toggleButtonSaveAsSVG = setToggleButtonStyle(menuItemSaveAsSVG, toggleButtonSaveAsSVG);
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
		markAsDirty();
	}

	@FXML
	private void handleSaveAsSVG() {
		menuItemSaveAsSVG.setSelected(!menuItemSaveAsSVG.isSelected());
		toggleButtonSaveAsSVG = setToggleButtonStyle(menuItemSaveAsSVG, toggleButtonSaveAsSVG);
		ltTree.setSaveAsSVG(menuItemSaveAsSVG.isSelected());
		markAsDirty();
		treeDescription.requestFocus();
	}

	@FXML
	private void handleMenuDrawAsType() {
		applicationPreferences.setDrawAsType(menuItemDrawAsType.isSelected());
	}

	@FXML
	private void handleMenuShowMatchingParenWithArrowKeys() {
		toggleButtonShowMatchingParenWithArrowKeys = setToggleButtonStyle(
				menuItemShowMatchingParenWithArrowKeys, toggleButtonShowMatchingParenWithArrowKeys);
		applicationPreferences
				.setShowMatchingParenWithArrowKeys(menuItemShowMatchingParenWithArrowKeys
						.isSelected());
	}

	@FXML
	private void handleShowMatchingParenWithArrowKeys() {
		menuItemShowMatchingParenWithArrowKeys.setSelected(!menuItemShowMatchingParenWithArrowKeys
				.isSelected());
		toggleButtonShowMatchingParenWithArrowKeys = setToggleButtonStyle(
				menuItemShowMatchingParenWithArrowKeys, toggleButtonShowMatchingParenWithArrowKeys);
		applicationPreferences
				.setShowMatchingParenWithArrowKeys(menuItemShowMatchingParenWithArrowKeys
						.isSelected());
		treeDescription.requestFocus();
	}

	@FXML
	private void handleMenuShowFullFilePath() {
		applicationPreferences.setShowFullFilePath(menuItemShowFullFilePath.isSelected());
		mainApp.updateStageTitle(mainApp.getTreeDataFile());
	}


	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		if (fIsDirty) {
			askAboutSaving();
		}
		System.exit(0);
	}

	/**
	 * Creates an empty tree.
	 */
	@FXML
	public void handleNewTree() {
		if (fIsDirty) {
			askAboutSaving();
		}
		String sDirectoryPath = applicationPreferences.getLastOpenedDirectoryPath();
		if (sDirectoryPath == "") {
			// probably creating a new file the first time the program is run;
			// set the directory to the closest we can to a reasonable default
			sDirectoryPath = tryToGetDefaultDirectoryPath();
		}
		applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
		File fileCreated = ControllerUtilities.doFileSaveAs(mainApp, currentLocale, false,
				lingTreeFilterDescription, RESOURCE_FACTORY.getStringBinding("file.new").get(),
				Constants.LINGTREE_DATA_FILE_EXTENSION, Constants.LINGTREE_DATA_FILE_EXTENSIONS,
				Constants.RESOURCE_LOCATION);
		if (fileCreated != null) {
			final String initialDescription = "( )";
			ltTree = new LingTreeTree();
			applicationPreferences.getSavedTreeParameters(ltTree);
			ltTree.setDescription(initialDescription);
			updateAllFontInfos();
			treeDescription.replaceText(initialDescription);
			treeDescription.moveTo(1);
			treeDescription.requestFocus();
			menuItemUseFlatTree.setSelected(ltTree.isShowFlatView());
			toggleButtonUseFlatTree.setSelected(ltTree.isShowFlatView());
			menuItemUseRightToLeftOrientation.setSelected(ltTree.isUseRightToLeftOrientation());
			menuItemSaveAsPng.setSelected(ltTree.isSaveAsPng());
			toggleButtonSaveAsPng.setSelected(ltTree.isSaveAsPng());
			menuItemSaveAsSVG.setSelected(ltTree.isSaveAsSVG());
			toggleButtonSaveAsSVG.setSelected(ltTree.isSaveAsSVG());
			mainApp.updateStageTitle(fileCreated);
			cleanDrawingArea();
			mainApp.getXmlBackEndProvider().setLingTree(ltTree);
			try {
				handleSaveTree();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			computeHighlighting();
		} else {
			ltTree = null;
		}
	}

	private void updateAllFontInfos() {
		updateFontInfoValues(AbbreviationFontInfo.getInstance(), ltTree.getAbbreviationFontInfo());
		updateFontInfoValues(EmptyElementFontInfo.getInstance(), ltTree.getEmptyElementFontInfo());
		updateFontInfoValues(GlossFontInfo.getInstance(), ltTree.getGlossFontInfo());
		updateFontInfoValues(LexFontInfo.getInstance(), ltTree.getLexicalFontInfo());
		updateFontInfoValues(NonTerminalFontInfo.getInstance(), ltTree.getNonTerminalFontInfo());
	}

	private void updateFontInfoValues(FontInfo fiUsedWhenDrawing, FontInfo fiFromTree) {
		fiUsedWhenDrawing.setColor(fiFromTree.getColor());
		fiUsedWhenDrawing.setFontFamily(fiFromTree.getFontFamily());
		fiUsedWhenDrawing.setFontSize(fiFromTree.getFontSize());
		fiUsedWhenDrawing.setFontType(fiFromTree.getFontType());
	}

	@FXML
	public void handleQuickReferenceGuide() {
		try {
			// Load the fxml file and create a new stage for the popup.
			Stage dialogStage = new Stage();
			String resource = "fxml/QuickReferenceGuide.fxml";
			String title = RESOURCE_FACTORY.getStringBinding("quick.title").get();
			FXMLLoader loader = ControllerUtilities.getLoader(mainApp, currentLocale, dialogStage,
					title, RootLayoutController.class.getResource(resource),
					Constants.RESOURCE_LOCATION);

			QuickReferenceGuideController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(mainApp);
			dialogStage.setResizable(true);
			dialogStage.initModality(Modality.NONE);
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a FileChooser to let the user select a tree to load.
	 */
	@FXML
	public void handleOpenTree() {
		if (fIsDirty) {
			askAboutSaving();
		}
		doFileOpen(false);
		setTree(mainApp.getTree());
		computeHighlighting();
		treeDescription.requestFocus();
		treeDescription.moveTo(0);
		handleDrawTree();
	}

	public File doFileOpen(Boolean fCloseIfCanceled) {
		File file = ControllerUtilities.getFileToOpen(mainApp, lingTreeFilterDescription,
				Constants.LINGTREE_DATA_FILE_EXTENSIONS);
		if (file != null) {
			mainApp.loadTreeData(file);
			String sDirectoryPath = file.getParent();
			applicationPreferences.setLastOpenedDirectoryPath(sDirectoryPath);
		} else if (fCloseIfCanceled) {
			// probably first time running and user chose to open a file
			// but then canceled. We quit.
			System.exit(0);
		}
		return file;
	}

	/**
	 * Saves the file to the tree file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 *
	 * @throws IOException
	 */
	@FXML
	public void handleSaveTree() throws IOException {
		updateTreeDataToBackEndProvider();
		File file = mainApp.getTreeDataFile();
		if (file != null) {
			mainApp.saveTreeData(file);
			markAsClean();
		} else {
			handleSaveTreeAs();
		}

		if (menuItemSaveAsPng.isSelected()) {
			// make sure the tree has been drawn
			handleDrawTree();
			if (TreeBuilder.getNumberOfErrors() == 0) {
				saveTreeAsPng();
			}
		}

		if (menuItemSaveAsSVG.isSelected()) {
			saveTreeAsSVG();
		}
		treeDescription.requestFocus();
	}

	private void saveTreeAsPng() throws IOException {
		File file = mainApp.getTreeDataFile();
		if (file == null) {
			return;
		}
		GraphicImageSaver saver = GraphicImageSaver.getInstance();
		saver.setFile(file);
		saver.saveAsPNG(drawingArea, ltTree);
	}

	private void saveTreeAsSVG() throws IOException {
		File file = mainApp.getTreeDataFile();
		if (file == null) {
			return;
		}
		GraphicImageSaver saver = GraphicImageSaver.getInstance();
		saver.setFile(file);
		TreeDrawer drawer = drawTreePrep();
		saver.saveAsSVG(drawer);
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveTreeAs() {
		ControllerUtilities.doFileSaveAs(mainApp, currentLocale, false, lingTreeFilterDescription,
				null, Constants.LINGTREE_DATA_FILE_EXTENSION,
				Constants.LINGTREE_DATA_FILE_EXTENSIONS, Constants.RESOURCE_LOCATION);
		// TODO: make sure we know what the new file path is
		markAsClean();
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

	@FXML
	private void handleKeyboards() {
		try {
			// Load the fxml file and create a new stage for the popup.
			Stage dialogStage = new Stage();
			String resource = "fxml/KeyboardChooser.fxml";
			String title = RESOURCE_FACTORY.getStringBinding("keyboarddialog.title").get();
			FXMLLoader loader = ControllerUtilities.getLoader(mainApp, currentLocale, dialogStage,
					title, RootLayoutController.class.getResource(resource),
					Constants.RESOURCE_LOCATION);

			KeyboardChooserController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setData(ltTree);
			dialogStage.setResizable(false);
			dialogStage.showAndWait();
			if (controller.isOkClicked()) {
				markAsDirty();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Change interface language.
	 */
	@FXML
	private void handleChangeInterfaceLanguage() {
		// apparently changing the locale can reset the tree contents so
		// we ask about saving the data first
		if (fIsDirty) {
			askAboutSaving();
		}

		Map<String, ResourceBundle> validLocales = ControllerUtilities.getValidLocales(
				currentLocale, Constants.RESOURCE_LOCATION);
		ChoiceDialog<String> dialog = new ChoiceDialog<>(
				currentLocale.getDisplayLanguage(currentLocale), validLocales.keySet());
		dialog.setTitle(RESOURCE_FACTORY.getStringBinding("dialog.chooseinterfacelanguage").get());
		dialog.setHeaderText(RESOURCE_FACTORY.getStringBinding("dialog.chooseinterfacelanguage")
				.get());
		dialog.setContentText(RESOURCE_FACTORY.getStringBinding("dialog.chooselanguage").get());
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(locale -> {
			Locale selectedLocale = validLocales.get(locale).getLocale();
			bundle = validLocales.get(locale);
			if (!currentLocale.equals(selectedLocale)) {
				mainApp.setLocale(selectedLocale);
				RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(Constants.RESOURCE_LOCATION,
						selectedLocale));
				ControllerUtilities.adjustMenusIfNeeded(mainApp.getOperatingSystem(), mainPane);
				currentLocale = selectedLocale;
			}
		});
	}

	private void insertMatchingClosingParenthesis() {
		if (menuItemDrawAsType.isSelected()) {
			int i = treeDescription.getCaretPosition();
			String contents = treeDescription.getText();
			contents = contents.substring(0, i) + " )" + contents.substring(i);
			treeDescription.replaceText(contents);
			treeDescription.moveTo(i);
		}
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
	public void handleAbbreviationFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getAbbreviationFontInfo());
		ltTree.setAbbreviationFontInfo(fontInfo);
		AbbreviationFontInfo.getInstance().setFont(fontInfo.getFont());
		AbbreviationFontInfo.getInstance().setColor(fontInfo.getColor());
		computeHighlighting();
		handleDrawTree();
		markAsDirty();
	}

	@FXML
	public void handleEmptyElementFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(),
				ltTree.getEmptyElementFontInfo());
		ltTree.setEmptyElementFontInfo(fontInfo);
		EmptyElementFontInfo.getInstance().setFont(fontInfo.getFont());
		EmptyElementFontInfo.getInstance().setColor(fontInfo.getColor());
		computeHighlighting();
		handleDrawTree();
		markAsDirty();
	}

	@FXML
	public void handleGlossFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getGlossFontInfo());
		ltTree.setGlossFontInfo(fontInfo);
		GlossFontInfo.getInstance().setFont(fontInfo.getFont());
		GlossFontInfo.getInstance().setColor(fontInfo.getColor());
		computeHighlighting();
		handleDrawTree();
		markAsDirty();
	}

	@FXML
	public void handleLexicalFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getLexicalFontInfo());
		ltTree.setLexicalFontInfo(fontInfo);
		LexFontInfo.getInstance().setFont(fontInfo.getFont());
		LexFontInfo.getInstance().setColor(fontInfo.getColor());
		computeHighlighting();
		handleDrawTree();
		markAsDirty();
	}

	@FXML
	public void handleNonTerminalFontInfo() {
		FontInfo fontInfo = showFontInfo(mainApp.getPrimaryStage(), ltTree.getNonTerminalFontInfo());
		ltTree.setNonTerminalFontInfo(fontInfo);
		NonTerminalFontInfo.getInstance().setFont(fontInfo.getFont());
		NonTerminalFontInfo.getInstance().setColor(fontInfo.getColor());
		computeHighlighting();
		handleDrawTree();
		markAsDirty();
	}

	public FontInfo showFontInfo(Stage stage, FontInfo fontInfo) {
//		// TODO: improve the font selector dialog so that one can type the font
//		// family name
//		// Can we improve the color picker to use color names, too?
		FontSelectorDialogWithColor dlg = new FontSelectorDialogWithColor(fontInfo.getFont(),
				fontInfo.getColor(), bundle);
		dlg.initOwner(stage);
		dlg.showAndWait();
		Font chosenFont = dlg.getResult();
		if (chosenFont != null) {
			fontInfo.setFont(chosenFont);
			Color color = dlg.getColor();
			fontInfo.setColor(color);
			markAsDirty();
		}
		return fontInfo;
	}

	public void handleTreeSpacingParameters() {
		try {
			// Load the fxml file and create a new stage for the popup.
			Stage dialogStage = new Stage();
			String resource = "fxml/TreeSpacingParametersChooser.fxml";
			String title = RESOURCE_FACTORY.getStringBinding("spacingdialog.title").get();
			FXMLLoader loader = ControllerUtilities.getLoader(mainApp, currentLocale, dialogStage,
					title, RootLayoutController.class.getResource(resource),
					Constants.RESOURCE_LOCATION);

			TreeSpacingParametersController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setData(ltTree);
			dialogStage.setResizable(false);
			dialogStage.showAndWait();
			if (controller.isOkClicked()) {
				handleDrawTree();
				markAsDirty();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleBackgroundAndLineParameters() {
		try {
			// Load the fxml file and create a new stage for the popup.
			Stage dialogStage = new Stage();
			String resource = "fxml/BackgroundAndLineParametersChooser.fxml";
			String title = RESOURCE_FACTORY.getStringBinding("backlinedialog.title").get();
			FXMLLoader loader = ControllerUtilities.getLoader(mainApp, currentLocale, dialogStage,
					title, RootLayoutController.class.getResource(resource),
					Constants.RESOURCE_LOCATION);

			BackgroundAndLineParametersController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setData(ltTree);
			dialogStage.setResizable(false);
			dialogStage.showAndWait();
			if (controller.isOkClicked()) {
				handleDrawTree();
				markAsDirty();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// code taken from
	// http://bekwam.blogspot.com/2014/10/cut-copy-and-paste-from-javafx-menubar.html
	@FXML
	public void handleShowingEditMenu() {
		if (systemClipboard == null) {
			systemClipboard = Clipboard.getSystemClipboard();
		}

		if (systemClipboard.hasString()) {
			adjustForClipboardContents();
		} else {
			adjustForEmptyClipboard();
		}

		if (anythingSelected()) {
			adjustForSelection();

		} else {
			adjustForDeselection();
		}
		if (treeDescription.isUndoAvailable()) {
			menuItemEditUndo.setDisable(false);
		} else {
			menuItemEditUndo.setDisable(true);
		}
		if (treeDescription.isRedoAvailable()) {
			menuItemEditRedo.setDisable(false);
		} else {
			menuItemEditRedo.setDisable(true);
		}
	}

	// **************************************************
	// code taken from
	// http://bekwam.blogspot.com/2014/10/cut-copy-and-paste-from-javafx-menubar.html
	public void adjustForEmptyClipboard() {
		menuItemEditPaste.setDisable(true); // nothing to paste
	}

	// code taken from
	// http://bekwam.blogspot.com/2014/10/cut-copy-and-paste-from-javafx-menubar.html
	private void adjustForClipboardContents() {
		menuItemEditPaste.setDisable(false); // something to paste
	}

	// code taken from
	// http://bekwam.blogspot.com/2014/10/cut-copy-and-paste-from-javafx-menubar.html
	private void adjustForSelection() {
		menuItemEditCut.setDisable(false);
		menuItemEditCopy.setDisable(false);
	}

	// code taken from
	// http://bekwam.blogspot.com/2014/10/cut-copy-and-paste-from-javafx-menubar.html
	private void adjustForDeselection() {
		menuItemEditCut.setDisable(true);
		menuItemEditCopy.setDisable(true);
	}

	public void setViewItemUsed(int value) {
		// default is to do nothing
	}

	boolean anythingSelected() {
		String sSelected = treeDescription.getSelectedText();
		if (!StringUtilities.isNullOrEmpty(sSelected)) {
			return true;
		}
		return false;
	}
}
