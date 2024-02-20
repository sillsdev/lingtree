// Copyright (c) 2024 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.lingtree.view;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

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

	KeyboardInfo lexicalKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	KeyboardInfo glossKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	KeyboardInfo nonTerminalKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	KeyboardInfo emptyElementKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	KeyboardInfo syntagmemeKeyboardInfo = new KeyboardInfo(new Locale("en"), "English"); 
	private Text keyboardText = new Text();
	
	Stage dialogStage;
	private boolean okClicked = false;
	private LingTreeTree ltTree;
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
		lexicalKeyboardInfo = ltTree.getLexicalKeyboard();
		lexical.setValue(lexicalKeyboardInfo);
		glossKeyboardInfo = ltTree.getGlossKeyboard();
		gloss.setValue(glossKeyboardInfo);
		nonTerminalKeyboardInfo = ltTree.getNonTerminalKeyboard();
		nonterminal.setValue(nonTerminalKeyboardInfo);
		emptyElementKeyboardInfo = ltTree.getEmptyElementKeyboard();
		emptyelement.setValue(emptyElementKeyboardInfo);
		syntagmemeKeyboardInfo = ltTree.getSyntagmemeKeyboard();
		syntagmeme.setValue(syntagmemeKeyboardInfo);
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks OK.
	 */
	@FXML
	private void handleOk() {
		ltTree.setLexicalKeyboard(lexicalKeyboardInfo);
		ltTree.setGlossKeyboard(glossKeyboardInfo);
		ltTree.setNonTerminalKeyboard(nonTerminalKeyboardInfo);
		ltTree.setEmptyElementKeyboard(emptyElementKeyboardInfo);
		ltTree.setSyntagmemeKeyboard(syntagmemeKeyboardInfo);
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
	}

}
