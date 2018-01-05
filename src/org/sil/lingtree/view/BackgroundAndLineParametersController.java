// Copyright (c) 2018 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.lingtree.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import org.sil.lingtree.MainApp;
import org.sil.lingtree.model.LingTreeTree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class BackgroundAndLineParametersController implements Initializable {

	@FXML
	private Label prompt;
	@FXML
	private ColorPicker backgroundColor;
	@FXML
	private TextField lineWidth;
	@FXML
	private ColorPicker lineColor;

	Stage dialogStage;
	private boolean okClicked = false;
	private MainApp mainApp;
	private LingTreeTree ltTree;
	private UnaryOperator<TextFormatter.Change> filter;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		filter = new UnaryOperator<TextFormatter.Change>() {
			@Override
			public TextFormatter.Change apply(TextFormatter.Change change) {
				String text = change.getText();
				for (int i = 0; i < text.length(); i++) {
					if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != '.' && text.charAt(i) != '-')
						return null;
				}
				return change;
			}
		};

		lineWidth.setTextFormatter(new TextFormatter<String>(filter));
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
		backgroundColor.setValue(ltTree.getBackgroundColor());
		lineWidth.setText(String.valueOf(ltTree.getLineWidth()));
		lineColor.setValue(ltTree.getLineColor());
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
		ltTree.setBackgroundColor(backgroundColor.getValue());
		if (lineWidth.getText().length() > 0) {
			ltTree.setLineWidth(Double.valueOf(lineWidth.getText()));
		}
		ltTree.setLineColor(lineColor.getValue());
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

}
