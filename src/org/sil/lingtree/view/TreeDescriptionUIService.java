/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import java.util.ResourceBundle;

import org.sil.lingtree.MainApp;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
public class TreeDescriptionUIService {

	private static TextArea treeDescription;
	private static ResourceBundle bundle;
	private static Image mainIcon;

	// TODO: is treating these as static the best way to go?
	// should we use a singleton pattern instead?
	public static void processRightParenthesis(TextArea description, ResourceBundle resource,
			Image image) {
		treeDescription = description;
		bundle = resource;
		mainIcon = image;
		int iRightParenthesis = treeDescription.getCaretPosition();
		int iLeftParenthesis = findMatchingLeftParenthesisAndHighlightIt(iRightParenthesis);
		// treeDescription.positionCaret(iRightParenthesis+7);
		if (iLeftParenthesis > -1) {
			// sleep for 750 milliseconds and then reset the caret
			Task<Void> timer = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
					}
					return null;
				}
			};
			timer.setOnSucceeded(event -> removeMatchingLeftParenthesisHighlightAndRestoreCaret(
					iLeftParenthesis, iRightParenthesis));
			new Thread(timer).start();
		}
	}

	private static Object removeMatchingLeftParenthesisHighlightAndRestoreCaret(
			int iLeftParenthesis, int iRightParenthesis) {
		// treeDescription.replaceText(iLeftParenthesis, iLeftParenthesis+7,
		// "(");
		treeDescription.positionCaret(iRightParenthesis);

		return null;
	}

	// TODO: when we get rtf working, highlight some other way and we may not
	// need to
	// return an integer here...
	// is public for unit testing
	public static int findMatchingLeftParenthesisAndHighlightIt(int iRightParenthesis) {
		int iIndex;
		String sDescription = treeDescription.getText().substring(0,
				Math.max(0, iRightParenthesis - 1));
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
			// treeDescription.replaceSelection("-->(<--");
			return iIndex;
		} else {
			if (bundle != null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(MainApp.kApplicationTitle);
				alert.setHeaderText(bundle.getString("error.nomatchingopeningparenthesis"));
				alert.setContentText(bundle.getString("error.missingopenparenthesis"));

				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(mainIcon);

				alert.showAndWait();
			}
		}
		// rtbTreeDescription.Select(Math.max(0, iCurrent - 1), 1);
		// rtbTreeDescription.SelectionFont = m_fntSynTagmeme;
		// rtbTreeDescription.SelectionColor = m_clrSynTagmeme;
		// rtbTreeDescription.Select(iCurrent, 0);
		return -1;
	}

	public static void processLeftParenthesis(TextArea description, ResourceBundle resource,
			Image image) {
		treeDescription = description;
		bundle = resource;
		mainIcon = image;
		int iLeftParenthesis = treeDescription.getCaretPosition();
		int iRightParenthesis = findMatchingRightParenthesisAndHighlightIt(iLeftParenthesis);
		// treeDescription.positionCaret(iRightParenthesis+7);
		if (iRightParenthesis > -1) {
			// sleep for 750 milliseconds and then reset the caret
			Task<Void> timer = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
					}
					return null;
				}
			};
			timer.setOnSucceeded(event -> removeMatchingRightParenthesisHighlightAndRestoreCaret(
					iLeftParenthesis, iRightParenthesis));
			new Thread(timer).start();
		}
	}

	private static Object removeMatchingRightParenthesisHighlightAndRestoreCaret(
			int iLeftParenthesis, int iRightParenthesis) {
		// treeDescription.replaceText(iLeftParenthesis, iLeftParenthesis+7,
		// "(");
		treeDescription.positionCaret(iLeftParenthesis);

		return null;
	}

	// TODO: when we get rtf working, highlight some other way and we may not
	// need to
	// return an integer here...
	// is public for unit testing
	public static int findMatchingRightParenthesisAndHighlightIt(int iLeftParenthesis) {
		int iIndex;
		String sDescription = treeDescription.getText();
		int iEnd = sDescription.length();
		int iOpenParen = 0;
		iIndex = iLeftParenthesis;
		while (iIndex < iEnd) {
			if (sDescription.charAt(iIndex) == '(') {
				iOpenParen++;
			} else if (sDescription.charAt(iIndex) == ')') {
				if (iOpenParen == 0) {
					break;
				} else {
					iOpenParen--;
				}
			}
			iIndex++;
		}
		if (iIndex < iEnd) {
			treeDescription.positionCaret(iIndex);
			treeDescription.selectForward();
			// treeDescription.replaceSelection("-->(<--");
			return iIndex;
		} else {
			if (bundle != null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(MainApp.kApplicationTitle);
				alert.setHeaderText(bundle.getString("error.nomatchingclosingparenthesis"));
				alert.setContentText(bundle.getString("error.missingcloseparenthesis"));

				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(mainIcon);

				alert.showAndWait();
			}
		}
		// rtbTreeDescription.Select(Math.max(0, iCurrent - 1), 1);
		// rtbTreeDescription.SelectionFont = m_fntSynTagmeme;
		// rtbTreeDescription.SelectionColor = m_clrSynTagmeme;
		// rtbTreeDescription.Select(iCurrent, 0);
		return -1;
	}

}
