/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree;

/**
 * @author Andy Black
 *
 */
public class Constants {
	public static final String VERSION_NUMBER = "1.0.0.0";
	
	public static final String SUBSCRIPT = "/s";
	public static final String SUPERSCRIPT = "/S";
	public static final String RESOURCE_LOCATION = "org.sil.lingtree.resources.LingTree";

	public static final String LINGTREE_DATA_FILE_EXTENSION = "tre";
	public static final String LINGTREE_DATA_FILE_EXTENSIONS = "*."
			+ LINGTREE_DATA_FILE_EXTENSION;
	public static final String LINGTREE_STARTER_FILE = "resources/starterFile.tre";
	public static final String DEFAULT_DIRECTORY_NAME = "My LingTree";

	public static final String SVG_HEADER="﻿<?xml version='1.0' standalone='no'?>\n" +
			"<svg width='{0}' height='{1}' version='1.1' xmlns='http://www.w3.org/2000/svg' contentScriptType='text/javascript'>\n" +
			"<script  id=\"clientEventHandlersJS\">\n" +
			"function OnClickLingTreeNode(node){}\n" +
			"</script>";
	public static final String SVG_END_ELEMENT="</svg>\n";

	// Unit Testing constants
	public static final String UNIT_TEST_DATA_FILE_NAME = "test/org/sil/lingtree/testData/TestData.";
	public static final String UNIT_TEST_DATA_FILE = "test/org/sil/lingtree/testData/TestData.tre";
}
