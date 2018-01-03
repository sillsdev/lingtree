/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Andy Black
 *
 */
public class ColorXmlAdaptor extends XmlAdapter<String, Color> {

	@Override
	public Color unmarshal(String colorAsString) throws Exception {
		return Color.web(colorAsString);
	}

	@Override
	public String marshal(Color color) throws Exception {
		int iRed = convertColorRGBToHexInt(color.getRed());
		int iGreen = convertColorRGBToHexInt(color.getGreen());
		int iBlue = convertColorRGBToHexInt(color.getBlue());
		String hex = String.format("#%02x%02x%02x", iRed, iGreen, iBlue);
		return hex;
	}
	
	private int convertColorRGBToHexInt(double d) {
		int iResult = Integer.valueOf((int) Math.round(d * 256));
		iResult = (iResult == 0) ? 0 : iResult - 1;
		return iResult;
	}
}
