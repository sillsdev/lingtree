/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.NodeType;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class LingTreeNode extends Node {
	
	private ObservableList<LingTreeNode> daughters = FXCollections.observableArrayList();
	// TODO: do we need this or can we use the id of Node?
	private String id; // id of the node
	private NodeType nodeType = NodeType.values()[0]; // node type (non-terminal, lex, gloss)
	
	private boolean fTriangle; // draw triangle instead of line
	private boolean fOmitLine; // no line above node
	
	private int iIndex; // index of node within its tree
	private double dXCoord; // left horizontal position of the node
	private double dXMid; // mid horizontal position of the node
	private double dYCoord; // upper vertical position of the node
	private double dYLowerMid; // lower mid position of the node for drawing a line below the node
	private double dYUpperMid; // upper mid position of the node for drawing a line above the node

	// TODO: Can we use an embedded Text item for these?
	private double dHeight; // height of the node
	private double dWidth; // width of the node
	Text contentTextBox = new Text(0, 0, "");      // content of the node
	Text subscriptTextBox = new Text(0, 0, "");    // subscript at the end of the node content
	Text superscriptTextBox = new Text(0, 0, "");  // superscript at the end of the node content
	
	private int iLevel; // level (or depth) of the node within the tree
	
	// TODO: why these and not a (Observable?) List<LingTreeNode>? or some such?
	//private LingTreeNode daughter; // leftmost daughter of this node in the tree
	private LingTreeNode rightSister; // immediate sister to the right of this node in the tree
	private LingTreeNode mother; // mother of this node in the tree
	
	// TODO: what are these for?
	private static final double dYCoordAdjustment = 40; // adjustment value
	private static final double dTriangleOffset = 300;

	public String getContent() {
		return contentTextBox.getText();
	}
	public void setContent(String content) {
		contentTextBox.setText(content);
	}
	public String getSubscript() {
		return subscriptTextBox.getText();
	}
	public void setSubscript(String subscript) {
		subscriptTextBox.setText(subscript);
	}
	public String getSuperscript() {
		return superscriptTextBox.getText();
	}
	public void setSuperscript(String superscript) {
		superscriptTextBox.setText(superscript);
	}
	public NodeType getNodeType() {
		return nodeType;
	}
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
	public boolean isTriangle() {
		return fTriangle;
	}
	public void setTriangle(boolean fTriangle) {
		this.fTriangle = fTriangle;
	}
	public boolean isOmitLine() {
		return fOmitLine;
	}
	public void setOmitLine(boolean fOmitLine) {
		this.fOmitLine = fOmitLine;
	}
	public LingTreeNode getRightSister() {
		return rightSister;
	}
	public void setRightSister(LingTreeNode sister) {
		this.rightSister = sister;
	}
	public LingTreeNode getMother() {
		return mother;
	}
	public void setMother(LingTreeNode mother) {
		this.mother = mother;
	}
	public int getiLevel() {
		return iLevel;
	}
	public void setiLevel(int iLevel) {
		this.iLevel = iLevel;
	}
	public ObservableList<LingTreeNode> getDaughters() {
		return daughters;
	}
	public void setDaughters(ObservableList<LingTreeNode> daughters) {
		this.daughters = daughters;
	}
	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_createPeer()
	 */
	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_computeGeomBounds(com.sun.javafx.geom.BaseBounds, com.sun.javafx.geom.transform.BaseTransform)
	 */
	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_computeContains(double, double)
	 */
	@Override
	protected boolean impl_computeContains(double localX, double localY) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see javafx.scene.Node#impl_processMXNode(com.sun.javafx.jmx.MXNodeAlgorithm, com.sun.javafx.jmx.MXNodeAlgorithmContext)
	 */
	@Override
	public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
