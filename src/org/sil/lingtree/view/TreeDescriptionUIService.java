/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import java.util.List;
import java.util.ResourceBundle;

import org.fxmisc.richtext.InlineCssTextArea;
import org.sil.lingtree.MainApp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Andy Black
 *
 */
public class TreeDescriptionUIService {

	private static InlineCssTextArea treeDescription;
	private static ResourceBundle bundle;
	private static Image mainIcon;
	private static List<KeyEvent> itemsKeyedDuringPause;

	public static void setItemsKeyedDuringPause(List<KeyEvent> itemsKeyedDuringPause) {
		TreeDescriptionUIService.itemsKeyedDuringPause = itemsKeyedDuringPause;
	}

	// TODO: is treating these as static the best way to go?
	// should we use a singleton pattern instead?
	/**
	 * @param description
	 *            = tree description text area
	 * @param iRightParenthesis
	 *            = position of right parenthesis to use when searching for
	 *            matching left parenthesis
	 * @param fCaretAfterParen
	 *            = whether the current cart is before the paren (left-arrow was
	 *            keyed) or after the paren (a ')' was keyed)
	 * @param pause
	 *            = number of milliseconds to sleep while showing matching paren
	 * @param resource
	 *            = resources used in message
	 * @param image
	 *            = image used in message
	 */
	public static void processRightParenthesis(InlineCssTextArea description, int iRightParenthesis,
			boolean fCaretAfterParen, double pause, ResourceBundle resource, Image image) {
		treeDescription = description;
		bundle = resource;
		mainIcon = image;
		treeDescription.setEditable(false);
		int iLeftParenthesis = findMatchingLeftParenthesisAndHighlightIt(iRightParenthesis);
		if (iLeftParenthesis > -1) {
			// sleep and then reset the caret
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(pause), event -> {
				removeMatchingLeftParenthesisHighlightAndRestoreCaret(iLeftParenthesis,
						iRightParenthesis + (fCaretAfterParen ? 1 : 0));
					treeDescription.setEditable(true);
					processAnyItemsKeyedDuringPause();
				}));
			timeline.play();
		} else {
			treeDescription.setEditable(true);
		}
	}

	private static void processAnyItemsKeyedDuringPause() {
		if (itemsKeyedDuringPause != null && itemsKeyedDuringPause.size() > 0) {
			for (KeyEvent keyEvent : itemsKeyedDuringPause) {
				if (keyEvent.getCharacter().equals("(")) {
					int i = treeDescription.getCaretPosition();
					treeDescription.insertText(i, "(");
				} else if (keyEvent.getCharacter().equals(")")) {
					int i = treeDescription.getCaretPosition();
					treeDescription.insertText(i, ")");
				} else {
					KeyEvent newEvent = new KeyEvent(keyEvent.getSource(), keyEvent.getTarget(),
							keyEvent.getEventType(), keyEvent.getCharacter(), keyEvent.getText(),
							keyEvent.getCode(), keyEvent.isShiftDown(), keyEvent.isControlDown(),
							keyEvent.isAltDown(), keyEvent.isMetaDown());
					treeDescription.fireEvent(newEvent);
				}
			}
			itemsKeyedDuringPause.clear();
		}
	}

	private static Object removeMatchingLeftParenthesisHighlightAndRestoreCaret(
			int iLeftParenthesis, int iRightParenthesis) {
		treeDescription.requestFollowCaret();
		treeDescription.moveTo(iRightParenthesis);
		return null;
	}

	// TODO: when we get rtf working, highlight some other way and we may not
	// need to return an integer here...
	// is public for unit testing
	public static int findMatchingLeftParenthesisAndHighlightIt(int iRightParenthesis) {
		String sDescription = treeDescription.getText();
		int iMax = sDescription.length() - 1;
		int iIndex = iRightParenthesis - 1;
		if (iIndex > iMax) {
			return -1;
		}
		int iCloseParen = 0;
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
			treeDescription.requestFollowCaret();
			treeDescription.moveTo(iIndex);
			treeDescription.selectRange(iIndex, iIndex+1);
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
		return -1;
	}

	/**
	 * @param description
	 *            = tree description text area
	 * @param fShowMsg
	 *            = whether to show message about missing matching right
	 *            parenthesis or not
	 * @param pause
	 *            = number of milliseconds to sleep while showing matching paren
	 * @param resource
	 *            = resources used in message
	 * @param image
	 *            = image used in message
	 */
	public static void processLeftParenthesis(InlineCssTextArea description, boolean fShowMsg,
			double pause, ResourceBundle resource, Image image) {
		treeDescription = description;
		bundle = resource;
		mainIcon = image;
		int iLeftParenthesis = treeDescription.getCaretPosition();
		treeDescription.setEditable(false);
		int iRightParenthesis = findMatchingRightParenthesisAndHighlightIt(iLeftParenthesis,
				fShowMsg);
		if (iRightParenthesis > -1) {
			// sleep and then reset the caret
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(pause), event -> {
				removeMatchingRightParenthesisHighlightAndRestoreCaret(iLeftParenthesis,
						iRightParenthesis);
					treeDescription.setEditable(true);
					processAnyItemsKeyedDuringPause();
				}));
			timeline.play();
		} else {
			treeDescription.setEditable(true);
		}
	}

	private static Object removeMatchingRightParenthesisHighlightAndRestoreCaret(
			int iLeftParenthesis, int iRightParenthesis) {
		treeDescription.requestFollowCaret();
		treeDescription.moveTo(iLeftParenthesis);
		return null;
	}

	// TODO: when we get rtf working, highlight some other way and we may not
	// need to return an integer here...
	// is public for unit testing
	/**
	 * @param iLeftParenthesis
	 *            = position of left parenthesis to match
	 * @param fShowMsg
	 *            = whether to show message about missing matching right
	 *            parenthesis
	 * @return
	 */
	public static int findMatchingRightParenthesisAndHighlightIt(int iLeftParenthesis,
			boolean fShowMsg) {
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
			treeDescription.requestFollowCaret();
			treeDescription.moveTo(iIndex);
			treeDescription.selectRange(iIndex, iIndex+1);
			return iIndex;
		} else {
			if (fShowMsg && bundle != null) {
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
