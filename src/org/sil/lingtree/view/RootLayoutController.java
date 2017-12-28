/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.ResourceBundle;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.sil.lingtree.MainApp;
import org.sil.lingtree.descriptionparser.DescriptionLexer;
import org.sil.lingtree.descriptionparser.DescriptionParser;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.service.BuildTreeFromDescriptionListener;
import org.sil.lingtree.service.TreeDrawer;

import com.sun.javafx.scene.control.skin.SplitPaneSkin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
//		handleSaveProject();
		System.exit(0);
	}

	@FXML
	private void handleButton() {
		LingTreeTree ltTree = parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))");
		TreeDrawer drawer = new TreeDrawer(ltTree);
		drawer.calculateMaxHeightPerLevel();
		drawer.calculateYCoordinateOfEveryNode();
		drawer.calculateXCoordinateOfEveryNode();
		Stage stage = mainApp.getPrimaryStage(); 
		Scene scene = stage.getScene();
		BorderPane root = (BorderPane) scene.getRoot();
		VBox vbox = (VBox) root.getChildren().get(2); // center
		AnchorPane anchor = (AnchorPane) vbox.getChildren().get(0);
		SplitPane split = (SplitPane) anchor.getChildren().get(0);
		Parent parent = (Parent) split.getChildrenUnmodifiable().get(1);
		anchor = (AnchorPane) parent.getChildrenUnmodifiable().get(0);
		Pane pane = (Pane) anchor.getChildren().get(0);
		drawer.draw(pane);
	}
	protected LingTreeTree parseAString(String sInput) {
		CharStream input = CharStreams.fromString(sInput);
		DescriptionLexer lexer = new DescriptionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DescriptionParser parser = new DescriptionParser(tokens);

		// begin parsing at rule 'description'
		ParseTree parseTree = parser.description();
		int numErrors = parser.getNumberOfSyntaxErrors();
		assertEquals(0, numErrors);
		ParseTreeWalker walker = new ParseTreeWalker(); // create standard
														// walker
		BuildTreeFromDescriptionListener validator = new BuildTreeFromDescriptionListener(parser);
		walker.walk(validator, parseTree); // initiate walk of tree with
											// listener
		LingTreeTree ltTree = validator.getTree();

		return ltTree;
	}

}
