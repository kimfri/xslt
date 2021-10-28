<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <students>
            <xsl:apply-templates/>
        </students>
    </xsl:template>
    <xsl:template match="courses/course/students/student">
        <student>
            <xsl:attribute name="id">
                <xsl:value-of select="@id"/>
            </xsl:attribute>
            <firstname>
                <xsl:value-of select="first"/>
            </firstname>
        </student>
    </xsl:template>
</xsl:stylesheet>
