/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import java.util.List;
import org.sil.lingtree.model.FontInfo;
import org.sil.utility.StringUtilities;

/**
 * @author Andy Black
 * Singleton pattern for inserting font info in a tree description
 */
public class FontInfoInserter {
    private static FontInfoInserter instance;
    
    private FontInfoInserter(){
    }
    
    public static FontInfoInserter getInstance(){
        if(instance == null){
            instance = new FontInfoInserter();
        }
        return instance;
    }
    
    public String insert(FontInfo fontInfo, String description, int lineNum, int charPos) {
    	StringBuilder sb = new StringBuilder();
    	List<String> lines = description.lines().toList();
    	lineNum--; // lineNum is one-based, not zero-based
    	for (int i = 0; i < lines.size(); i++) {
        	String sLine = lines.get(i);
        	if (i == lineNum) {
            	String newLine = sLine.substring(0, charPos);
            	String fiRep = produceFontInfoRepresentation(fontInfo);
            	String restOfLine = sLine.substring(charPos);
            	sb.append(newLine);
            	sb.append(fiRep);
            	sb.append(restOfLine);
        	} else {
        		sb.append(sLine);
        	}
           	sb.append("\n");
    	}
    	return sb.toString();
    }

    public String produceFontInfoRepresentation(FontInfo fontInfo) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("/f");
    	String fontType = fontInfo.getFontType();
    	sb.append(produceFontTypeRepresentation(fontType));
    	sb.append("|c");
    	sb.append(StringUtilities.toRGBCode(fontInfo.getColor()));
    	sb.append("|f");
    	sb.append(fontInfo.getFontFamily());
    	sb.append("|s");
    	sb.append(fontInfo.getFontSize());
    	sb.append("/F");
    	return sb.toString();
    }
  
    protected String produceFontTypeRepresentation(String fontType) {
		StringBuilder sb = new StringBuilder();
		if (fontType.contains("Regular")) {
			sb.append("|r");
		}
		if (fontType.contains("Bold")) {
			sb.append("|b");
		}
		if (fontType.contains("Italic")) {
			sb.append("|i");
		}
		return sb.toString();
	}
}
