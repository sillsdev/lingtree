/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import org.sil.lingtree.descriptionparser.antlr4generated.DescriptionParser;
import org.sil.lingtree.model.FontInfo;
import org.sil.lingtree.model.LingTreeNode;
import org.sil.utility.StringUtilities;

import javafx.scene.paint.Color;

/**
 * @author Andy Black
 * Singleton pattern for parsing a custom font info string
 */
public class CustomFontInfoParser {
    private static CustomFontInfoParser instance;
    LingTreeNode node;
    
    private CustomFontInfoParser(){
    }
    
    public static CustomFontInfoParser getInstance(){
        if(instance == null){
            instance = new CustomFontInfoParser();
        }
        return instance;
    }

    public FontInfo parseString(String sDescription, LingTreeNode node, DescriptionParser parser) {
    	if (StringUtilities.isNullOrEmpty(sDescription)) {
    		if (parser != null)
    			parser.notifyErrorListeners("custom font info missing");
    		return null;
    	}
    	FontInfo typeFontInfo = node.getFontInfoFromNodeType();
    	FontInfo fontInfo = new FontInfo(typeFontInfo.getFontFamily(), typeFontInfo.getFontSize(), typeFontInfo.getFontType());
    	fontInfo.setColor(typeFontInfo.getColor());
    	String[] items = sDescription.split("\\|");
    	String type = "";
    	if (items.length == 0) {
    		if (parser != null)
    			parser.notifyErrorListeners("custom font info missing");
    		return null;
    	}
    	for (String item : items) {
    		if (item.length() == 0)
    			continue;
    		String code = item.substring(0, 1);
			switch (code) {
			case "b":
				type = fontInfo.getFontType().equals("Italic") ? "Bold Italic" : "Bold";
				fontInfo.setFontType(type);
				break;
			case "c":
				String sColor = item.substring(1, item.length());
				Color color = Color.valueOf(sColor);
				fontInfo.setColor(color);
				break;
			case "f":
				String fontFamily = item.substring(1, item.length());;
				fontInfo.setFontFamily(fontFamily);
				break;
			case "i":
				type = fontInfo.getFontType().equals("Bold") ? "Bold Italic" : "Italic";
				fontInfo.setFontType(type);
				break;
			case "r":
				fontInfo.setFontType("Regular");
				break;
			case "s":
				String sSize = item.substring(1, item.length());
				double size = Double.parseDouble(sSize);
				fontInfo.setFontSize(size);
				break;
			default:
				if (item.length() == 0 && parser != null) {
					parser.notifyErrorListeners("custom font info missing key character ('b', 'i', 'c', 's', or 'f')");
					return null;
				}
			}
    	}
    	node.setCustomFontInfo(fontInfo);
    	return fontInfo;
    }

}

