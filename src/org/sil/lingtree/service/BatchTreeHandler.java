/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

//import org.junit.Rule;
import org.sil.lingtree.Constants;
import org.sil.lingtree.backendprovider.XMLBackEndProvider;
import org.sil.lingtree.model.LingTreeTree;
//import org.sil.lingtree.view.JavaFXThreadingRule;

/**
 * @author Andy Black
 *
 */
public class BatchTreeHandler {
//	@Rule
//	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	LingTreeTree ltTree;
	Locale locale;
	File treeFile;
	XMLBackEndProvider xmlBackEndProvider;
	ResourceBundle bundle;

	public BatchTreeHandler(String treeFilePath, ResourceBundle bundle) {
		if (bundle == null) {
			// Unit test case
			locale = new Locale("en");
			bundle = ResourceBundle.getBundle(Constants.RESOURCE_LOCATION, locale);
		}
		this.bundle = bundle;
		locale = bundle.getLocale();
		ltTree = new LingTreeTree();
		xmlBackEndProvider = new XMLBackEndProvider(ltTree, locale);
		treeFile = new File(treeFilePath);
	}

	public boolean fileExists() {
		if (treeFile == null) {
			return false;
		}
		return treeFile.exists();
	}

	public void processTree(boolean fTesting) throws IOException {
		if (!fileExists()) {
			String sPrompt = bundle.getString("batch.prompt");
			String sMsg = bundle.getString("batch.filenotfound") + treeFile.getPath();
			System.out.println(sMsg);
			if (!fTesting) {
				JOptionPane.showMessageDialog(null, sMsg, sPrompt, JOptionPane.ERROR_MESSAGE);
			}
			return;
		}
		xmlBackEndProvider.loadTreeDataFromFile(treeFile);
		ltTree = xmlBackEndProvider.getLingTree();
		ltTree.setFontsAndColors();
		ltTree = TreeBuilder.parseAString(ltTree.getDescription(), ltTree);
		if (TreeBuilder.getNumberOfErrors() > 0) {
			reportErrorMessage();
			return;
		}
		GraphicImageSaver saver = GraphicImageSaver.getInstance();
		saver.setFile(treeFile);
		SVGDrawer drawer = new SVGDrawer(ltTree);
		if (drawer != null) {
			if (ltTree.isSaveAsSVG()) {
				saver.saveAsSVG(drawer);
			}
			if (ltTree.isSaveAsPng()) {
				Platform.runLater(new Runnable() {
	                 @Override public void run() {
	     				Pane drawingArea = new Pane();
	    				drawer.draw(drawingArea);
	    				saver.saveAsPNG(drawingArea, ltTree);
	                 }
	             });
			}
		}
	}

	public void reportErrorMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(bundle.getString("batch.couldnotprocesstreedescription"));
		String sSyntaxErrorMessage = TreeBuilder.buildErrorMessage(bundle);
		sb.append(TreeBuilder.buildErrorMessagePart1(sSyntaxErrorMessage, bundle));
		sb.append(TreeBuilder.getDescriptionBeforeMark());
		sb.append(" " + bundle.getString("descriptionsyntaxerror.here"));
		sb.append(TreeBuilder.getDescriptionAfterMark());
		System.out.println(sb.toString());
	}
}
