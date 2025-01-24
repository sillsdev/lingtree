/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.NodeText;
import org.sil.lingtree.model.SubOrSuperscriptText;
import org.sil.lingtree.model.SubscriptText;
import org.sil.lingtree.model.SuperscriptText;

import javafx.scene.text.Text;

/**
 * 
 */
public class NodeTextFinder {
	
    private static NodeTextFinder instance;
    
    private NodeTextFinder(){
    }
    
    public static NodeTextFinder getInstance(){
        if(instance == null){
        	instance = new NodeTextFinder();
        }
        return instance;
    }
  
	public NodeText findNodeTextInNodeAround(LingTreeNode node, double xcoord, double ycoord) {
		NodeText result = null;
		for (NodeText nodeText : node.getContentsAsList()) {
			if (isWithinBounds(nodeText, xcoord, ycoord)) {
				return nodeText;
			}
		}
		return result;
	}

	public SubOrSuperscriptText findSubOrSuperscriptTextInNodeAround(LingTreeNode node, double xcoord, double ycoord) {
		SubOrSuperscriptText result = null;
		if (node.hasSubscript()) {
			SubscriptText sub = node.getSubscriptText();
			if (isWithinBounds(sub, xcoord, ycoord)) {
				return sub;
			}
		}
		if (node.hasSuperscript()) {
			SuperscriptText sup = node.getSuperscriptText();
			if (isWithinBounds(sup, xcoord, ycoord)) {
				return sup;
			}
		}
		return result;
	}
    
    private boolean isWithinBounds(NodeText text, double xcoord, double ycoord) {
    	Text textBox = text.getTextBox();
    	if (xcoord < textBox.getX() || xcoord > (textBox.getX() + textBox.getLayoutBounds().getWidth())) {
    		return false;
    	}
    	if (ycoord < textBox.getY() - textBox.getLayoutBounds().getHeight() || ycoord > (textBox.getY())) {
    		return false;
    	}
    	return true;
    }
}
