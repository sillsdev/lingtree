<?xml version="1.0" encoding="UTF-8"?>
<!-- transform to convert LingTree file from dbversion 2 to 3
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="no"/>
    <xsl:variable name="gloss" select="//glossFontInfo"/>
    <xsl:template match="@* |  node()">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="/lingTreeTree[@dbversion='2']">
        <lingTreeTree dbversion="3">
            <abbreviationFontInfo>
                <color><xsl:value-of select="$gloss/color"/></color>
                <fontFamily><xsl:value-of select="$gloss/fontFamily"/></fontFamily>
                <fontSize><xsl:value-of select="$gloss/fontSize"/></fontSize>
                <fontType><xsl:value-of select="$gloss/fontType"/></fontType>
            </abbreviationFontInfo>
            <xsl:apply-templates/>
        </lingTreeTree>
    </xsl:template>
</xsl:stylesheet>
