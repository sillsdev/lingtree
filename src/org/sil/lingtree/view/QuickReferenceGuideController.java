// Copyright (c) 2018 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.lingtree.view;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.sil.lingtree.MainApp;
import org.sil.lingtree.ApplicationPreferences;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Andy Black
 *
 */
public class QuickReferenceGuideController implements Initializable {

	@FXML
	WebView browser;
	@FXML
	WebEngine webEngine;

	MainApp mainApp;
	Stage dialogStage;
	private ResourceBundle bundle;
	private Locale locale;
	private ApplicationPreferences preferences;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		bundle = resources;
		// browser = new WebView();
		webEngine = browser.getEngine();
		String html = formatQuickReferenceGuide();
		webEngine.loadContent(html);
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		this.dialogStage.initStyle(StageStyle.UTILITY);
		this.dialogStage.setOnCloseRequest(event -> {
			preferences.setLastWindowParameters(ApplicationPreferences.LAST_QUICK_REFERENCE_GUIDE, dialogStage);
		});
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		preferences = mainApp.getApplicationPreferences();
		dialogStage = preferences.getLastWindowParameters(ApplicationPreferences.LAST_QUICK_REFERENCE_GUIDE, dialogStage, 485.0, 580.0);
	}

	private String formatQuickReferenceGuide() {
		StringBuilder sb = new StringBuilder();
		formatHTMLBeginning(sb);
		formatTitle(sb);
		formatTable(sb);
		formatHTMLEnding(sb);
		return sb.toString();
	}

	private void formatHTMLBeginning(StringBuilder sb) {
		sb.append("<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n<title>");
		sb.append(bundle.getString("quick.title"));
		sb.append("</title>\n<style type=\"text/css\">\n");
		sb.append("body {background-color:#fffff5;}\n");
		sb.append("h1 {color:#663399;}\n");
		sb.append("th {text-align:left; vertical-align:bottom;}\n");
		sb.append("td {vertical-align:top; padding-left:.25in; text-indent:-.25in;}\n");
		sb.append("</style>\n</head>\n<body>\n");
	}

	private void formatTitle(StringBuilder sb) {
		sb.append("<h1 align=\"center\">");
		sb.append(bundle.getString("quick.title"));
		sb.append("</h1><hr/>\n");
	}

	private void formatTable(StringBuilder sb) {
		formatTableHeader(sb);
		formatTableRow(sb, "(", "quick.anewtreelevel");
		formatTableRow(sb, ")", "quick.endatreelevel");
		formatTableRow(sb, "\\E", "quick.emptynode");
		formatTableRow(sb, "\\G", "quick.glossnode");
		formatTableRow(sb, "\\L", "quick.lexicalnode");
		formatTableRow(sb, "\\O", "quick.omitlineabove");
		formatTableRow(sb, "\\T", "quick.triangleover");
		formatTableRow(sb, "/s", "quick.subscript");
		formatTableRow(sb, "/_", "quick.subscriptitalic");
		formatTableRow(sb, "/S", "quick.superscript");
		formatTableRow(sb, "/^", "quick.superscriptitalic");
		formatTableRow(sb, "\\(", "quick.openparenthesis");
		formatTableRow(sb, "\\)", "quick.closeparenthesis");
		sb.append("</table>\n");
	}

	private void formatTableHeader(StringBuilder sb) {
		sb.append("<table>\n<tr><th>");
		sb.append(bundle.getString("quick.keythis"));
		sb.append("</th><th>&nbsp;</th><th>");
		sb.append(bundle.getString("quick.togetthis"));
		sb.append("</th></tr>\n");
	}

	private void formatTableRow(StringBuilder sb, String keySequence, String resultKey) {
		sb.append("<tr><td>");
		sb.append(keySequence);
		sb.append("</td><td>&nbsp;</td><td>");
		sb.append(bundle.getString(resultKey));
		sb.append("</td></tr>\n");
	}

	private void formatHTMLEnding(StringBuilder sb) {
		sb.append("<hr/>\n<div align=\"center\">Copyright &copy; 2002-2018 SIL International</div></body>\n</html>\n");
	}

}
