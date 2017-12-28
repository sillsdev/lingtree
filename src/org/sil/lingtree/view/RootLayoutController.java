/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.sil.lingtree.MainApp;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.service.TreeBuilder;
import org.sil.lingtree.service.TreeDrawer;

import com.sun.javafx.scene.control.skin.SplitPaneSkin;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class RootLayoutController implements Initializable {

	MainApp mainApp;

	@FXML
	MenuItem menuItemDrawTree;

	@FXML
	TextArea treeDescription;
	@FXML
	Pane drawingArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
				treeDescription.setPromptText("Enter description here");
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

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		// handleSaveProject();
		System.exit(0);
	}

	@FXML
	private void handleDrawTree() {
		drawingArea.getChildren().clear();
		String sDescription = treeDescription.getText();
		if (sDescription.length() == 0) {
			sDescription = "(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))";
		}
		LingTreeTree ltTree = TreeBuilder.parseAString(sDescription);
		TreeDrawer drawer = new TreeDrawer(ltTree);
		drawer.draw(drawingArea);
	}

	private void processRightParenthesis() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				int iRightParenthesis = treeDescription.getCaretPosition();
				findMatchingLeftParenthesisAndHighlightIt(iRightParenthesis);
				treeDescription.positionCaret(iRightParenthesis);
//				try {
//					findMatchingLeftParenthesisAndHighlightIt(iRightParenthesis);
//					//Thread thisThread = Thread.currentThread();// .sleep(750);
//					//Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} finally {
//				}
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
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(MainApp.kApplicationTitle);
			alert.setHeaderText("No matching opening parenthesis!");
			alert.setContentText("Missing Open Parenthesis!");

			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			//stage.getIcons().add(getNewMainIconImage());

			alert.showAndWait();
		}
		// rtbTreeDescription.Select(Math.max(0, iCurrent - 1), 1);
		// rtbTreeDescription.SelectionFont = m_fntSynTagmeme;
		// rtbTreeDescription.SelectionColor = m_clrSynTagmeme;
		// rtbTreeDescription.Select(iCurrent, 0);
	}
}
