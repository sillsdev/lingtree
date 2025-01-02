/**
 * Copyright (c) 2016-2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.model;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 * Singleton pattern for font information for gloss nodes
 */
public class GlossFontInfo extends FontInfo {

    private static GlossFontInfo instance;
    
    private GlossFontInfo(){
    	super("Arial", 12.0, "Regular");
    	setColor(Color.GREEN);
    }
    
    public static GlossFontInfo getInstance(){
        if(instance == null){
            instance = new GlossFontInfo();
        }
        return instance;
    }
}
