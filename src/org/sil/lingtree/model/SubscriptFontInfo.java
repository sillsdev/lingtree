/**
 * Copyright (c) 2016-2017 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.model;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 * Singleton pattern for font information for subscript text in a node
 */
public class SubscriptFontInfo extends FontInfo {

    private static SubscriptFontInfo instance;
    
    private SubscriptFontInfo(){
    	super("Times New Roman", 9.0, "Italic");
    	setColor(Color.BROWN);
    }
    
    public static SubscriptFontInfo getInstance(){
        if(instance == null){
            instance = new SubscriptFontInfo();
        }
        return instance;
    }
}
