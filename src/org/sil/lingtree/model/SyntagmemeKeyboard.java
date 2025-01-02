/**
 * Copyright (c) 2024-2025 SIL Global
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
public class SyntagmemeKeyboard extends KeyboardInfo {

    private static SyntagmemeKeyboard instance;
    
    private SyntagmemeKeyboard(){
    	super(new Locale("en"), "English", 0);
    }
    
    public static SyntagmemeKeyboard getInstance(){
        if(instance == null){
            instance = new SyntagmemeKeyboard();
        }
        return instance;
    }

}
