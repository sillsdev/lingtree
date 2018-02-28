<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Migrate LiingTree data from version 1 (unmarked) to version 2

    Change element names, remove version and custom colors, and rework font info to be nested
    Add subscript and superscript font info
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output encoding="UTF-8" method="xml" indent="no"/>
    <xsl:param name="dpi" select="96"/>

    <!-- 
        LingTreeTree 
    -->
    <xsl:template match="LingTreeTree">
        <lingTreeTree dbversion="2">
            <xsl:apply-templates/>
            <emptyElementFontInfo>
                <color>
                    <xsl:value-of select="LexColorArgb"/>
                </color>
                <fontFamily>
                    <xsl:value-of select="LexFontFace"/>
                </fontFamily>
                <fontSize>
                    <xsl:value-of select="LexFontSize"/>
                </fontSize>
                <fontType>
                    <xsl:value-of select="translate(LexFontStyle, ',','')"/>
                </fontType>
            </emptyElementFontInfo>
            <glossFontInfo>
                <color>
                    <xsl:value-of select="GlossColorArgb"/>
                </color>
                <fontFamily>
                    <xsl:value-of select="GlossFontFace"/>
                </fontFamily>
                <fontSize>
                    <xsl:value-of select="GlossFontSize"/>
                </fontSize>
                <fontType>
                    <xsl:value-of select="translate(GlossFontStyle, ',','')"/>
                </fontType>
            </glossFontInfo>
            <lexicalFontInfo>
                <color>
                    <xsl:value-of select="LexColorArgb"/>
                </color>
                <fontFamily>
                    <xsl:value-of select="LexFontFace"/>
                </fontFamily>
                <fontSize>
                    <xsl:value-of select="LexFontSize"/>
                </fontSize>
                <fontType>
                    <xsl:value-of select="translate(LexFontStyle, ',','')"/>
                </fontType>
            </lexicalFontInfo>
            <nonTerminalFontInfo>
                <color>
                    <xsl:value-of select="NTColorArgb"/>
                </color>
                <fontFamily>
                    <xsl:value-of select="NTFontFace"/>
                </fontFamily>
                <fontSize>
                    <xsl:value-of select="NTFontSize"/>
                </fontSize>
                <fontType>
                    <xsl:value-of select="translate(NTFontStyle, ',','')"/>
                </fontType>
            </nonTerminalFontInfo>
        </lingTreeTree>
    </xsl:template>

    <xsl:template match="BackgroundColorArgb">
        <backgroundColor>
            <xsl:value-of select="."/>
        </backgroundColor>
    </xsl:template>
    
    <xsl:template match="Description">
        <description>
            <xsl:value-of select="."/>
        </description>
    </xsl:template>
    
    <xsl:template match="HorizontalGap">
        <horizontalGap>
            <xsl:call-template name="ConvertToPixels">
                <xsl:with-param name="hundredthsOfMM" select="."/>
            </xsl:call-template>
        </horizontalGap>
    </xsl:template>

    <xsl:template match="HorizontalOffset">
        <horizontalOffset>
            <xsl:value-of select="."/>
        </horizontalOffset>
    </xsl:template>

    <xsl:template match="InitialXCoord">
        <initialXCoordinate>
            <xsl:call-template name="ConvertToPixels">
                <xsl:with-param name="hundredthsOfMM" select="."/>
            </xsl:call-template>
        </initialXCoordinate>
    </xsl:template>

    <xsl:template match="InitialYCoord">
        <initialYCoordinate>
            <xsl:variable name="NTFontSize" select="//NTFontSize"/>
            <xsl:call-template name="ConvertToPixels">
                <xsl:with-param name="hundredthsOfMM" select="."/>
                <xsl:with-param name="augment" select="$NTFontSize *.75"/>
            </xsl:call-template>
        </initialYCoordinate>
    </xsl:template>

    <xsl:template match="LexGlossGapAdjustment">
        <lexGlossGapAdjustment>
            <xsl:call-template name="ConvertToPixels">
                <xsl:with-param name="hundredthsOfMM" select="."/>
            </xsl:call-template>
        </lexGlossGapAdjustment>
    </xsl:template>

    <xsl:template match="LinesColorArgb">
        <lineColor>
            <xsl:value-of select="."/>
        </lineColor>
    </xsl:template>

    <xsl:template match="LineWidth">
        <lineWidth>
            <xsl:choose>
                <xsl:when test=".=0">
                    <xsl:text>1.0</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:call-template name="ConvertToPixels">
                        <xsl:with-param name="hundredthsOfMM" select="."/>
                    </xsl:call-template>
                </xsl:otherwise>
            </xsl:choose>
        </lineWidth>
        <saveAsPng>true</saveAsPng>
        <saveAsSVG>true</saveAsSVG>
    </xsl:template>

    <xsl:template match="ShowFlatView">
        <showFlatView>
            <xsl:value-of select="translate(.,'FT','ft')"/>
        </showFlatView>
    </xsl:template>

    <xsl:template match="VerticalGap">
        <verticalGap>
            <xsl:call-template name="ConvertToPixels">
                <xsl:with-param name="hundredthsOfMM" select="."/>
            </xsl:call-template>
        </verticalGap>
    </xsl:template>

    <!-- ignore these -->
    <xsl:template match="CustomColors"/>
    <xsl:template match="int"/>
    <xsl:template match="GlossColorArgb"/>
    <xsl:template match="GlossFontFace"/>
    <xsl:template match="GlossFontSize"/>
    <xsl:template match="GlossFontStyle"/>
    <xsl:template match="LexColorArgb"/>
    <xsl:template match="LexFontFace"/>
    <xsl:template match="LexFontSize"/>
    <xsl:template match="LexFontStyle"/>
    <xsl:template match="NTColorArgb"/>
    <xsl:template match="NTFontFace"/>
    <xsl:template match="NTFontSize"/>
    <xsl:template match="NTFontStyle"/>
    <xsl:template match="TrySmoothing"/>
    <xsl:template match="TryPixelOffset"/>
    <xsl:template match="Version"/>
    
    <!-- 
        ConvertToPixels
    -->
    <xsl:template name="ConvertToPixels">
        <xsl:param name="hundredthsOfMM"/>
        <xsl:param name="augment" select="0"/>
        <xsl:variable name="pixels" select="(($hundredthsOfMM div 2540) * $dpi) + $augment"/>
        <xsl:value-of select="format-number($pixels, '#.##')"/>
    </xsl:template>
</xsl:stylesheet>
