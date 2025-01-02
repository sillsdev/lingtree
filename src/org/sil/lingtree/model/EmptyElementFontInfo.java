/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.model;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 * Singleton pattern for font information for lexical nodes
 */
public class EmptyElementFontInfo extends FontInfo {

    private static EmptyElementFontInfo instance;
    
    private EmptyElementFontInfo(){
    	super("Charis SIL", 12.0, "Regular");
    	setColor(Color.BLUE);
    }
    
    public static EmptyElementFontInfo getInstance(){
        if(instance == null){
            instance = new EmptyElementFontInfo();
        }
        return instance;
    }
}
