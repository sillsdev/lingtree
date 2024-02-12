// Copyright (c) 2024 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.lingtree.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;





import org.sil.lingtree.MainApp;
import org.sil.lingtree.model.Keyboard;
import org.sil.lingtree.model.LexicalKeyboard;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.utility.service.keyboards.KeyboardHandler;
import org.sil.utility.service.keyboards.KeyboardInfo;
import org.sil.utility.service.keyboards.LinuxKeyboardHandler;
import org.sil.utility.service.keyboards.MacOSXKeyboardHandler;
import org.sil.utility.service.keyboards.WindowsKeyboardHandler;





import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @author Andy Black
 *
 */
public class KeyboardChooserController implements Initializable {

	@FXML
	private Label prompt;
	@FXML
	private ComboBox<KeyboardInfo> syntagmeme = new ComboBox<KeyboardInfo>();
	@FXML
	private ComboBox<KeyboardInfo> nonterminal = new ComboBox<KeyboardInfo>();
	@FXML
	private ComboBox<KeyboardInfo> lexical = new ComboBox<KeyboardInfo>();
	@FXML
	private ComboBox<KeyboardInfo> gloss = new ComboBox<KeyboardInfo>();
	@FXML
	private ComboBox<KeyboardInfo> emptyelement = new ComboBox<KeyboardInfo>();

	Keyboard lexicalKeyboard = new Keyboard();
	KeyboardInfo lexicalKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	Keyboard glossKeyboard = new Keyboard();
	KeyboardInfo glossKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	Keyboard nonTerminalKeyboard = new Keyboard();
	KeyboardInfo nonTerminalKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	Keyboard emptyElementKeyboard = new Keyboard();
	KeyboardInfo emptyElementKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	Keyboard syntagmemeKeyboard = new Keyboard();
	KeyboardInfo syntagmemeKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	private Text keyboardText = new Text();
	
	Stage dialogStage;
	private boolean okClicked = false;
	private MainApp mainApp;
	private LingTreeTree ltTree;
	private UnaryOperator<TextFormatter.Change> filter;
	KeyboardHandler keyboardHandler = new KeyboardHandler();
	LinuxKeyboardHandler linuxHandler = new LinuxKeyboardHandler();
	MacOSXKeyboardHandler macHandler = new MacOSXKeyboardHandler();
	WindowsKeyboardHandler winHandler = new WindowsKeyboardHandler();
	ObservableList<KeyboardInfo> activeKeyboards = FXCollections.observableArrayList();
	int numberOfKeyboards = 0;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		initKeyboardHandler();
		
		lexical.setItems(activeKeyboards);
		lexical.setConverter(new StringConverter<KeyboardInfo>() {
			@Override
			public String toString(KeyboardInfo object) { 
				return object.getDescription();
			}
			@Override
			public KeyboardInfo fromString(String string) {
				return null;
			}
		});
		lexical.valueProperty().addListener((observable, oldValue, newValue) -> {			
			keyboardText.setText(newValue.getDescription());
			lexicalKeyboardInfo = newValue;
			lexicalKeyboard = new Keyboard(newValue);
		});

		gloss.setItems(activeKeyboards);
		gloss.setConverter(new StringConverter<KeyboardInfo>() {
			@Override
			public String toString(KeyboardInfo object) { 
				return object.getDescription();
			}
			@Override
			public KeyboardInfo fromString(String string) {
				return null;
			}
		});
		gloss.valueProperty().addListener((observable, oldValue, newValue) -> {			
			keyboardText.setText(newValue.getDescription());
			glossKeyboardInfo = newValue;
			glossKeyboard = new Keyboard(newValue);
		});

		nonterminal.setItems(activeKeyboards);
		nonterminal.setConverter(new StringConverter<KeyboardInfo>() {
			@Override
			public String toString(KeyboardInfo object) { 
				return object.getDescription();
			}
			@Override
			public KeyboardInfo fromString(String string) {
				return null;
			}
		});
		nonterminal.valueProperty().addListener((observable, oldValue, newValue) -> {			
			keyboardText.setText(newValue.getDescription());
			nonTerminalKeyboardInfo = newValue;
			nonTerminalKeyboard = new Keyboard(newValue);
		});

		emptyelement.setItems(activeKeyboards);
		emptyelement.setConverter(new StringConverter<KeyboardInfo>() {
			@Override
			public String toString(KeyboardInfo object) { 
				return object.getDescription();
			}
			@Override
			public KeyboardInfo fromString(String string) {
				return null;
			}
		});
		emptyelement.valueProperty().addListener((observable, oldValue, newValue) -> {			
			keyboardText.setText(newValue.getDescription());
			emptyElementKeyboardInfo = newValue;
			emptyElementKeyboard = new Keyboard(newValue);
		});

		syntagmeme.setItems(activeKeyboards);
		syntagmeme.setConverter(new StringConverter<KeyboardInfo>() {
			@Override
			public String toString(KeyboardInfo object) { 
				return object.getDescription();
			}
			@Override
			public KeyboardInfo fromString(String string) {
				return null;
			}
		});
		syntagmeme.valueProperty().addListener((observable, oldValue, newValue) -> {			
			keyboardText.setText(newValue.getDescription());
			syntagmemeKeyboardInfo = newValue;
			syntagmemeKeyboard = new Keyboard(newValue);
		});
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setData(LingTreeTree ltTree) {
		this.ltTree = ltTree;
		lexicalKeyboard = ltTree.getLexicalKeyboard();
		KeyboardInfo ki = new KeyboardInfo(new Locale(lexicalKeyboard.getLocale()),
				lexicalKeyboard.getDescription(), lexicalKeyboard.getWindowsLangID());
		lexical.setValue(ki);
		glossKeyboard = ltTree.getGlossKeyboard();
		ki = new KeyboardInfo(new Locale(glossKeyboard.getLocale()),
				glossKeyboard.getDescription(), glossKeyboard.getWindowsLangID());
		gloss.setValue(ki);
		nonTerminalKeyboard = ltTree.getNonTerminalKeyboard();
		ki = new KeyboardInfo(new Locale(nonTerminalKeyboard.getLocale()),
				nonTerminalKeyboard.getDescription(), nonTerminalKeyboard.getWindowsLangID());
		nonterminal.setValue(ki);
		emptyElementKeyboard = ltTree.getEmptyElementKeyboard();
		ki = new KeyboardInfo(new Locale(emptyElementKeyboard.getLocale()),
				emptyElementKeyboard.getDescription(), emptyElementKeyboard.getWindowsLangID());
		emptyelement.setValue(ki);
		syntagmemeKeyboard = ltTree.getSyntagmemeKeyboard();
		ki = new KeyboardInfo(new Locale(syntagmemeKeyboard.getLocale()),
				syntagmemeKeyboard.getDescription(), syntagmemeKeyboard.getWindowsLangID());
		syntagmeme.setValue(ki);
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleSyntagmeme() {
//		syntagmeme.getItems().addAll(activeKeyboards);
//		syntagmeme.setItems(activeKeyboards);
//		syntagmeme.setValue(activeKeyboards.get(0));
//		System.out.println("handle syntagmeme");
	}

	@FXML
	private void handleNonterminal() {
//		nonterminal.getItems().addAll(activeKeyboards);
	}

	@FXML
	private void handleLexical() {
//		lexical.getItems().addAll(activeKeyboards);
	}

	@FXML
	private void handleGloss() {
//		gloss.getItems().addAll(activeKeyboards);
	}

	@FXML
	private void handleEmptyElement() {
//		emptyelement.getItems().addAll(activeKeyboards);
	}



	/**
	 * Called when the user clicks OK.
	 */
	@FXML
	private void handleOk() {
		ltTree.setLexicalKeyboard(lexicalKeyboard);
		ltTree.setGlossKeyboard(glossKeyboard);
		ltTree.setNonTerminalKeyboard(nonTerminalKeyboard);
		ltTree.setEmptyElementKeyboard(emptyElementKeyboard);
		ltTree.setSyntagmemeKeyboard(syntagmemeKeyboard);
		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Called when the user clicks help.
	 */
	@FXML
	private void handleHelp() {
		// TODO: write custom (English) documentation for this, showing examples
		//mainApp.showNotImplementedYet();
	}
	void initKeyboardHandler() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().contains("windows")) {
			keyboardHandler = winHandler;
		} else if (os.toLowerCase().contains("mac")) {
			keyboardHandler = macHandler;
		} else {
			keyboardHandler = linuxHandler;
		}
		activeKeyboards = FXCollections.observableArrayList(keyboardHandler.getAvailableKeyboards());
		numberOfKeyboards = activeKeyboards.size();
		for (KeyboardInfo ki : activeKeyboards) {
			System.out.println("ki=" + ki);
		}
	}

}
