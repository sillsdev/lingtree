/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.lingtree.model.LingTreeTree;

/**
 * @author Andy Black
 * Singleton pattern for font information for gloss nodes
 */
public class NodeFinder {


    private static NodeFinder instance;
    
    private NodeFinder(){
    }
    
    public static NodeFinder getInstance(){
        if(instance == null){
            instance = new NodeFinder();
        }
        return instance;
    }
    
    public LingTreeNode nodeAt(LingTreeTree tree, double xcoord, double ycoord) {
    	LingTreeNode result = null;
    	result = findNodeAround(tree.getRootNode(), xcoord, ycoord);
    	return result;
    }
    
    private LingTreeNode findNodeAround(LingTreeNode node, double xcoord, double ycoord) {
    	LingTreeNode result = null;
    	if (isWithinBounds(node, xcoord, ycoord)) {
    		return node;
    	}
    	for (LingTreeNode daughter : node.getDaughters()) {
    		result = findNodeAround(daughter, xcoord, ycoord);
    		if (result != null) {
    			break;
    		}
    	}
    	return result;
    }
    
    private boolean isWithinBounds(LingTreeNode node, double xcoord, double ycoord) {
    	if (xcoord < node.getXCoordinate() || xcoord > (node.getXCoordinate() + node.getWidth())) {
    		return false;
    	}
    	if (ycoord < node.getYCoordinate() - node.getHeight() || ycoord > (node.getYCoordinate() /*+ node.getHeight()*/)) {
    		return false;
    	}
    	return true;
    }
}
