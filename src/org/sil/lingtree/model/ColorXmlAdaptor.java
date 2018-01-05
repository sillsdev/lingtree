/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.model;

import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.sil.utility.StringUtilities;

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
		return StringUtilities.toRGBCode(color);
	}
}
