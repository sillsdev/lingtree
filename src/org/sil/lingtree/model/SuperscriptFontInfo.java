/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.model;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 * Singleton pattern for font information for superscript text in a node
 */
public class SuperscriptFontInfo extends FontInfo {

    private static SuperscriptFontInfo instance;
    
    private SuperscriptFontInfo(){
    	super("Times New Roman", 9.0, "Bold");
    	setColor(Color.RED);
    }
    
    public static SuperscriptFontInfo getInstance(){
        if(instance == null){
            instance = new SuperscriptFontInfo();
        }
        return instance;
    }
}
