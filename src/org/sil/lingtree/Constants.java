/**
 * Copyright (c) 2016-2020 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree;

/**
 * @author Andy Black
 *
 */
public class Constants {
	public static final String VERSION_NUMBER = "1.2.5";
	public static final int CURRENT_DATABASE_VERSION = 2;

	public static final String SUBSCRIPT = "/s";
	public static final String SUBSCRIPTITALIC = "/_";
	public static final String SUPERSCRIPT = "/S";
	public static final String SUPERSCRIPTITALIC = "/^";
	// LaTeX uses 70%; OpenOffice & LibreOffice use 58%; Word uses 50%
	//    (See https://en.wikipedia.org/wiki/Subscript_and_superscript#Desktop_publishing)
	// I liked 70% best on my laptop, but the jury is still out
	public static final double SUB_SUPER_SCRIPT_FONT_SIZE_FACTOR = .70;
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
			"</script>\n";
	public static final String SVG_BACKGROUND_COLOR="<rect width=\"100%\" height=\"100%\" fill=\"{0}\"/>\n";
	public static final String SVG_END_ELEMENT="</svg>\n";

	public static final String UTF8_ENCODING = "UTF8";
	public static final String MIGRATION_XSLT_FILE_NAME = "resources/DataMigration";
	public static final String RESOURCE_SOURCE_LOCATION = "src/org/sil/lingtree/";

	// Unit Testing constants
	public static final String UNIT_TEST_DATA_FILE_NAME = "test/org/sil/lingtree/testdata/TestData.";
	public static final String UNIT_TEST_DATA_FILE = "test/org/sil/lingtree/testdata/TestData.tre";
	public static final String UNIT_TEST_DATA_FILE_BAD_TREE = "test/org/sil/lingtree/testdata/TestDataBadTree.tre";
	public static final String UNIT_TEST_DATA_FILE_VERSION_000 = "test/org/sil/lingtree/testdata/TestDataVersion000.tre";
	public static final String UNIT_TEST_DATA_FILE_VERSION_001 = "test/org/sil/lingtree/testdata/TestDataVersion001.tre";
	public static final String UNIT_TEST_DATA_FILE_VERSION_002 = "test/org/sil/lingtree/testdata/TestDataVersion002.tre";
	public static final String UNIT_TEST_DATA_FILE_VERSION_2 = "test/org/sil/lingtree/testdata/TestDataVersion2.tre";
	public static final String UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_000 = "test/org/sil/lingtree/testdata/TestDataWedgesInDescriptionVersion000.tre";
	public static final String UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_001 = "test/org/sil/lingtree/testdata/TestDataWedgesInDescriptionVersion001.tre";
	public static final String UNIT_TEST_DATA_FILE_WITH_WEDGES_IN_DESCRIPTION_VERSION_002 = "test/org/sil/lingtree/testdata/TestDataWedgesInDescriptionVersion002.tre";
}
