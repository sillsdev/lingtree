/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.model;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 * Singleton pattern for font information for non-terminal nodes
 */
public class NonTerminalFontInfo extends FontInfo {

    private static NonTerminalFontInfo instance;
    
    private NonTerminalFontInfo(){
    	super("Times New Roman", 12.0, "Regular");
    	setColor(Color.BLACK);
    }
    
    public static NonTerminalFontInfo getInstance(){
        if(instance == null){
            instance = new NonTerminalFontInfo();
        }
        return instance;
    }
}
