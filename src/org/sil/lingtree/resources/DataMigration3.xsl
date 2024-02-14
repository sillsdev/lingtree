<?xml version="1.0" encoding="UTF-8"?>
<!-- transform to convert LingTree file from dbversion 3 to 4 -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="no" />
	<xsl:template match="@* |  node()">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="/lingTreeTree[@dbversion='3']">
		<lingTreeTree dbversion="4">
			<emptyElementKeyboard>
				<description>English</description>
				<locale>en</locale>
				<windowsLangID>0</windowsLangID>
			</emptyElementKeyboard>
			<glossKeyboard>
				<description>English</description>
				<locale>en</locale>
				<windowsLangID>0</windowsLangID>
			</glossKeyboard>
			<lexicalKeyboard>
				<description>English</description>
				<locale>en</locale>
				<windowsLangID>0</windowsLangID>
			</lexicalKeyboard>
			<nonTerminalKeyboard>
				<description>English</description>
				<locale>en</locale>
				<windowsLangID>0</windowsLangID>
			</nonTerminalKeyboard>
			<synTagmemeKeyboard>
				<description>English</description>
				<locale>en</locale>
				<windowsLangID>0</windowsLangID>
			</synTagmemeKeyboard>
			<xsl:apply-templates />
		</lingTreeTree>
	</xsl:template>
</xsl:stylesheet>
