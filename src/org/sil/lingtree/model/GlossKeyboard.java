/**
 * Copyright (c) 2024 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import java.util.Locale;

import org.sil.utility.service.keyboards.KeyboardInfo;

/**
 * @author Andy Black
 * Singleton pattern for keyboard information for gloss keyboard
 *
 */
public class GlossKeyboard extends KeyboardInfo {

    private static GlossKeyboard instance;
    
    private GlossKeyboard(){
    	super(new Locale("en"), "English", 0);
    }
    
    public static GlossKeyboard getInstance(){
        if(instance == null){
            instance = new GlossKeyboard();
        }
        return instance;
    }

}
