// Copyright (c) 2018-2024 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.lingtree.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import org.sil.lingtree.model.LingTreeTree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class TreeSpacingParametersController implements Initializable {

	@FXML
	private Label prompt;
	@FXML
	private TextField initialXCoordinate;
	@FXML
	private TextField initialYCoordinate;
	@FXML
	private TextField horizontalGap;
	@FXML
	private TextField verticalGap;
	@FXML
	private TextField lexGlossGapAdjustment;
	@FXML
	private TextField minimumXGapForExtraVerticalSpacing;
	@FXML
	private TextField verticalAdjustmentForExtraVerticalSpacing;

	Stage dialogStage;
	private boolean okClicked = false;
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

		initialXCoordinate.setTextFormatter(new TextFormatter<String>(filter));
		initialYCoordinate.setTextFormatter(new TextFormatter<String>(filter));
		horizontalGap.setTextFormatter(new TextFormatter<String>(filter));
		verticalGap.setTextFormatter(new TextFormatter<String>(filter));
		lexGlossGapAdjustment.setTextFormatter(new TextFormatter<String>(filter));
		minimumXGapForExtraVerticalSpacing.setTextFormatter(new TextFormatter<String>(filter));
		verticalAdjustmentForExtraVerticalSpacing.setTextFormatter(new TextFormatter<String>(filter));
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
		initialXCoordinate.setText(String.valueOf(ltTree.getInitialXCoordinate()));
		initialYCoordinate.setText(String.valueOf(ltTree.getInitialYCoordinate()));
		horizontalGap.setText(String.valueOf(ltTree.getHorizontalGap()));
		verticalGap.setText(String.valueOf(ltTree.getVerticalGap()));
		lexGlossGapAdjustment.setText(String.valueOf(ltTree.getLexGlossGapAdjustment()));
		minimumXGapForExtraVerticalSpacing.setText(String.valueOf(ltTree.getMinimumXGapForExtraVerticalSpacing()));
		verticalAdjustmentForExtraVerticalSpacing.setText(String.valueOf(ltTree.getVerticalAdjustmentForExtraVerticalSpacing()));
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
		if (initialXCoordinate.getText().length() > 0) {
			ltTree.setInitialXCoordinate(Double.valueOf(initialXCoordinate.getText()));
		}
		if (initialYCoordinate.getText().length() > 0) {
			ltTree.setInitialYCoordinate(Double.valueOf(initialYCoordinate.getText()));
		}
		if (horizontalGap.getText().length() > 0) {
			ltTree.setHorizontalGap(Double.valueOf(horizontalGap.getText()));
		}
		if (verticalGap.getText().length() > 0) {
			ltTree.setVerticalGap(Double.valueOf(verticalGap.getText()));
		}
		if (lexGlossGapAdjustment.getText().length() > 0) {
			ltTree.setLexGlossGapAdjustment(Double.valueOf(lexGlossGapAdjustment.getText()));
		}
		if (minimumXGapForExtraVerticalSpacing.getText().length() > 0) {
			ltTree.setMinimumXGapForExtraVerticalSpacing(Double.valueOf(minimumXGapForExtraVerticalSpacing.getText()));
		}
		if (verticalAdjustmentForExtraVerticalSpacing.getText().length() > 0) {
			ltTree.setVerticalAdjustmentForExtraVerticalSpacing(Double.valueOf(verticalAdjustmentForExtraVerticalSpacing.getText()));
		}
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
}
