/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.service;

import static org.junit.Assert.*;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.descriptionparser.DescriptionLexer;
import org.sil.lingtree.descriptionparser.DescriptionParser;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;
import org.sil.lingtree.model.NodeType;
import org.sil.lingtree.view.JavaFXThreadingRule;

/**
 * @author Andy Black
 *
 */
public class BuildTreeFromDescriptionListenerTest extends ServiceBaseTest {
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	@Test
	public void fontInfoTextTest() {
		// Non-terminal, lex, and gloss nodes
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(NP (\\L Juan (\\G John)))", origTree);
		LingTreeNode node = ltTree.getRootNode();
		checkFontInfo(node, node.getContentTextBox(), "Times New Roman", 12.0, "Regular", Color.BLACK);
		List<LingTreeNode> daughters = node.getDaughters();
		node = daughters.get(0);
		checkFontInfo(node, node.getContentTextBox(), "Charis SIL", 12.0, "Regular", Color.BLUE);
		daughters = node.getDaughters();
		node = daughters.get(0);
		checkFontInfo(node, node.getContentTextBox(), "Arial", 12.0, "Regular", Color.GREEN);

		// subscript and superscript
		ltTree = TreeBuilder.parseAString("(NP/si (N/S'))", ltTree);
		node = ltTree.getRootNode();
		checkFontInfo(node, node.getContentTextBox(), "Times New Roman", 12.0, "Regular", Color.BLACK);
		checkFontInfo(node, node.getSubscriptTextBox(), "Times New Roman", 9.0, "Italic", Color.BROWN);
		daughters = node.getDaughters();
		node = daughters.get(0);
		checkFontInfo(node, node.getContentTextBox(), "Times New Roman", 12.0, "Regular", Color.BLACK);
		checkFontInfo(node, node.getSuperscriptTextBox(), "Times New Roman", 9.0, "Bold", Color.RED);
	}

	private void checkFontInfo(LingTreeNode node, Text textBox, String fontFamily,
			double fontSize, String fontType, Color color) {
		Font font = textBox.getFont();
		assertEquals(fontFamily, font.getFamily());
		assertEquals(fontSize, font.getSize(), 0.0);
		assertEquals(fontType, font.getStyle());
		assertEquals(color, textBox.getFill());
	}
	
	@Test
	public void buildTreesTest() {
		// Basic example
		LingTreeTree origTree = new LingTreeTree();
		LingTreeTree ltTree = TreeBuilder.parseAString("(S (NP) (VP))", origTree);
		LingTreeNode rootNode = ltTree.getRootNode();
		checkNodeResult(rootNode, "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(rootNode.getMother());
		assertNull(rootNode.getRightSister());
		List<LingTreeNode> daughters = rootNode.getDaughters();
		LingTreeNode node1 = daughters.get(0);
		checkNodeResult(node1, "NP", "", "", false, false, NodeType.NonTerminal, 2, 0);
		checkNodeResult(node1.getMother(), "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		checkNodeResult(node1.getRightSister(), "VP", "", "", false, false, NodeType.NonTerminal, 2, 0);
		LingTreeNode node2 = daughters.get(1);
		checkNodeResult(node2, "VP", "", "", false, false, NodeType.NonTerminal, 2, 0);
		checkNodeResult(node2.getMother(), "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(node2.getRightSister());
		
		// lex/gloss example
		ltTree = TreeBuilder.parseAString("(S (NP (\\L Juan (\\G John))) (VP (V (\\L duerme (\\G sleeps)))))", origTree);
		// root node
		rootNode = ltTree.getRootNode();
		checkNodeResult(rootNode, "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(rootNode.getMother());
		assertNull(rootNode.getRightSister());
		daughters = rootNode.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "NP", "", "", false, false, NodeType.NonTerminal, 2, 1);
		checkNodeResult(node1.getMother(), "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		checkNodeResult(node1.getRightSister(), "VP", "", "", false, false, NodeType.NonTerminal, 2, 1);
		node2 = daughters.get(1);
		checkNodeResult(node2, "VP", "", "", false, false, NodeType.NonTerminal, 2, 1);
		checkNodeResult(node1.getMother(), "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(node2.getRightSister());
		// NP
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "Juan", "", "", false, false, NodeType.Lex, 3, 1);
		checkNodeResult(node1.getMother(), "NP", "", "", false, false, NodeType.NonTerminal, 2, 1);
		assertNull(node1.getRightSister());
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "John", "", "", false, false, NodeType.Gloss, 4, 0);
		checkNodeResult(node1.getMother(), "Juan", "", "", false, false, NodeType.Lex, 3, 1);
		assertNull(node1.getRightSister());
		// VP
		daughters = node2.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "V", "", "", false, false, NodeType.NonTerminal, 3, 1);
		checkNodeResult(node1.getMother(), "VP", "", "", false, false, NodeType.NonTerminal, 2, 1);
		assertNull(node1.getRightSister());
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "duerme", "", "", false, false, NodeType.Lex, 4, 1);
		checkNodeResult(node1.getMother(), "V", "", "", false, false, NodeType.NonTerminal, 3, 1);
		assertNull(node1.getRightSister());
		node1 = node1.getDaughters().get(0);
		checkNodeResult(node1, "sleeps", "", "", false, false, NodeType.Gloss, 5, 0);
		checkNodeResult(node1.getMother(), "duerme", "", "", false, false, NodeType.Lex, 4, 1);
		assertNull(node1.getRightSister());
		
		// triangle example
		ltTree = TreeBuilder.parseAString("(NP (\\T all the King’s men))", origTree);
		rootNode = ltTree.getRootNode();
		checkNodeResult(rootNode, "NP", "", "", false, false, NodeType.NonTerminal, 1, 1);
		assertNull(rootNode.getMother());
		assertNull(rootNode.getRightSister());
		daughters = rootNode.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "all the King’s men", "", "", false, true, NodeType.NonTerminal, 2, 0);
		checkNodeResult(node1.getMother(), "NP", "", "", false, false, NodeType.NonTerminal, 1, 1);
		assertNull(node1.getRightSister());
		
		// omit lines example
		ltTree = TreeBuilder.parseAString("((\\O σ (O (\\L t)) (N (R (\\L e)))) (\\O σ (O (\\L p)) (N (R (\\L i)) (C (\\L k)))))", origTree);
		// root node
		rootNode = ltTree.getRootNode();
		checkNodeResult(rootNode, "", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(rootNode.getMother());
		assertNull(rootNode.getRightSister());
		daughters = rootNode.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "σ", "", "", true, false, NodeType.NonTerminal, 2, 2);
		checkNodeResult(node1.getMother(), "", "", "", false, false, NodeType.NonTerminal, 1, 2);
		checkNodeResult(node1.getRightSister(), "σ", "", "", true, false, NodeType.NonTerminal, 2, 2);
		node2 = daughters.get(1);
		checkNodeResult(node2, "σ", "", "", true, false, NodeType.NonTerminal, 2, 2);
		checkNodeResult(node2.getMother(), "", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(node2.getRightSister());
		// not testing mother or right sister from here on down
		// O t
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		LingTreeNode node3 = daughters.get(1);
		checkNodeResult(node1, "O", "", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "t", "", "", false, false, NodeType.Lex, 4, 0);
		// N R e
		checkNodeResult(node3, "N", "", "", false, false, NodeType.NonTerminal, 3, 1);
		node3 = node3.getDaughters().get(0);
		checkNodeResult(node3, "R", "", "", false, false, NodeType.NonTerminal, 4, 1);
		node3 = node3.getDaughters().get(0);
		checkNodeResult(node3, "e", "", "", false, false, NodeType.Lex, 5, 0);
		// O p
		daughters = node2.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "O", "", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "p", "", "", false, false, NodeType.Lex, 4, 0);
		node1 = node2.getDaughters().get(1);
		checkNodeResult(node1, "N", "", "", false, false, NodeType.NonTerminal, 3, 2);
		node3 = node1.getDaughters().get(0);
		checkNodeResult(node3, "R", "", "", false, false, NodeType.NonTerminal, 4, 1);
		node3 = node3.getDaughters().get(0);
		checkNodeResult(node3, "i", "", "", false, false, NodeType.Lex, 5, 0);
		node3 = node1.getDaughters().get(1);
		checkNodeResult(node3, "C", "", "", false, false, NodeType.NonTerminal, 4, 1);
		node3 = node3.getDaughters().get(0);
		checkNodeResult(node3, "k", "", "", false, false, NodeType.Lex, 5, 0);
		
		// subscript and superscript example
		ltTree = TreeBuilder.parseAString("(S (NP/s1/S' (N (dogs))) (VP (V (chase)) (NP/S'/s2 (N (cats)))))", origTree);
		// root node
		rootNode = ltTree.getRootNode();
		checkNodeResult(rootNode, "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(rootNode.getMother());
		assertNull(rootNode.getRightSister());
		daughters = rootNode.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "NP", "1", "'", false, false, NodeType.NonTerminal, 2, 1);
		node2 = daughters.get(1);
		checkNodeResult(node2, "VP", "", "", false, false, NodeType.NonTerminal, 2, 2);
		// NP N dogs
		node1 = node1.getDaughters().get(0);
		checkNodeResult(node1, "N", "", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "dogs", "", "", false, false, NodeType.NonTerminal, 4, 0);
		// VP V chase
		node3 = node2.getDaughters().get(0);
		checkNodeResult(node3, "V", "", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node3.getDaughters();
		node3 = daughters.get(0);
		checkNodeResult(node3, "chase", "", "", false, false, NodeType.NonTerminal, 4, 0);
		// VP NP N cats
		node3 = node2.getDaughters().get(1);
		checkNodeResult(node3, "NP", "2", "'", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node3.getDaughters();
		node3 = daughters.get(0);
		checkNodeResult(node3, "N", "", "", false, false, NodeType.NonTerminal, 4, 1);
		daughters = node3.getDaughters();
		node3 = daughters.get(0);
		checkNodeResult(node3, "cats", "", "", false, false, NodeType.NonTerminal, 5, 0);
		
		// backslash and forward slash as text node content
		ltTree = TreeBuilder.parseAString("(S (/S'/Comp (N (do\\gs))) (VP (V (chase)) (/s2 (N (cats)))))", origTree);
		// root node
		rootNode = ltTree.getRootNode();
		checkNodeResult(rootNode, "S", "", "", false, false, NodeType.NonTerminal, 1, 2);
		assertNull(rootNode.getMother());
		assertNull(rootNode.getRightSister());
		daughters = rootNode.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "", "", "'/Comp", false, false, NodeType.NonTerminal, 2, 1);
		node2 = daughters.get(1);
		checkNodeResult(node2, "VP", "", "", false, false, NodeType.NonTerminal, 2, 2);
		// NP N dogs
		node1 = node1.getDaughters().get(0);
		checkNodeResult(node1, "N", "", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node1.getDaughters();
		node1 = daughters.get(0);
		checkNodeResult(node1, "do\\gs", "", "", false, false, NodeType.NonTerminal, 4, 0);
		// VP V chase
		node3 = node2.getDaughters().get(0);
		checkNodeResult(node3, "V", "", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node3.getDaughters();
		node3 = daughters.get(0);
		checkNodeResult(node3, "chase", "", "", false, false, NodeType.NonTerminal, 4, 0);
		// VP NP N cats
		node3 = node2.getDaughters().get(1);
		checkNodeResult(node3, "", "2", "", false, false, NodeType.NonTerminal, 3, 1);
		daughters = node3.getDaughters();
		node3 = daughters.get(0);
		checkNodeResult(node3, "N", "", "", false, false, NodeType.NonTerminal, 4, 1);
		daughters = node3.getDaughters();
		node3 = daughters.get(0);
		checkNodeResult(node3, "cats", "", "", false, false, NodeType.NonTerminal, 5, 0);
		
	}

	private void checkNodeResult(LingTreeNode node, String sContent, String sSubscript,
			String sSuperscript, boolean fOmitLine, boolean fTriangle, NodeType nodeType, int iLevel, int iNumDaughters) {
		assertNotNull(node);
		assertEquals(sContent, node.getContent());
		assertEquals(sSubscript, node.getSubscript());
		assertEquals(sSuperscript, node.getSuperscript());
		assertEquals(fOmitLine, node.isOmitLine());
		assertEquals(fTriangle, node.isTriangle());
		assertEquals(nodeType, node.getNodeType());
		assertEquals(iLevel, node.getLevel());
		List<LingTreeNode> daughters = node.getDaughters();
		assertEquals(iNumDaughters, daughters.size());
	}

}
