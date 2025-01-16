/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.model.AbbreviationText;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.NodeText;

import javafx.scene.text.Text;

/**
 * 
 */
public class AbbreviationFinder {
	
    private static AbbreviationFinder instance;
    
    private AbbreviationFinder(){
    }
    
    public static AbbreviationFinder getInstance(){
        if(instance == null){
        	instance = new AbbreviationFinder();
        }
        return instance;
    }
  
    public AbbreviationText findAbbrTextInNodeAround(LingTreeNode node, double xcoord, double ycoord) {
    	AbbreviationText result = null;
    	for (NodeText text : node.getContentsAsList()) {
    		if (text instanceof AbbreviationText abbrText) {
    			if (isWithinBounds(abbrText, xcoord, ycoord)) {
    				return abbrText;
    			}
    		}
    	}
    	return result;
    }
    
    private boolean isWithinBounds(AbbreviationText text, double xcoord, double ycoord) {
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
