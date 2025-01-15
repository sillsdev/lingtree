/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sil.lingtree.model.FontInfo;

import javafx.scene.paint.Color;

/**
 * 
 */
public class FontInfoInserterTest extends ServiceBaseTest {

	FontInfo fontInfo;
	FontInfoInserter fiInserter;
	String description = "";

	@Test
	public void produceFontInfoRepresentationTest() {
		fiInserter = FontInfoInserter.getInstance();
		checkRepresentation("Regular", "Times New Roman", 12.0, Color.RED, "/f|r|c#ff0000|fTimes New Roman|s12.0/F");
		checkRepresentation("Bold", "Times New Roman", 12.0, Color.BEIGE, "/f|b|c#f5f5dc|fTimes New Roman|s12.0/F");
		checkRepresentation("Italic", "Times New Roman", 22.0, Color.BEIGE, "/f|i|c#f5f5dc|fTimes New Roman|s22.0/F");
		checkRepresentation("Bold Italic", "Times New Roman", 22.0, Color.BEIGE, "/f|b|i|c#f5f5dc|fTimes New Roman|s22.0/F");
	}
	
	private void checkRepresentation(String type, String family, double size, Color color, String expected) {
		fontInfo = createFontInfo(type, family, size, color);
		String rep = fiInserter.produceFontInfoRepresentation(fontInfo);
		assertEquals(expected, rep);
	}
	
	private FontInfo createFontInfo(String type, String family, double size, Color color) {
		fontInfo = new FontInfo(family, size, type);
		fontInfo.setColor(color);
		return fontInfo;
	}
	
	@Test
	public void produceFontInfoRepresentationThatDiffersTest() {
		fiInserter = FontInfoInserter.getInstance();
		FontInfo oldFontInfo = createFontInfo("Regular", "Times New Roman", 12.0, Color.RED);
		FontInfo newFontInfo = createFontInfo("Regular", "Times New Roman", 12.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f/F");
		newFontInfo = createFontInfo("Bold", "Times New Roman", 12.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|b/F");
		newFontInfo = createFontInfo("Bold Italic", "Times New Roman", 12.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|b|i/F");
		newFontInfo = createFontInfo("Regular", "Charis SIL", 12.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|fCharis SIL/F");
		newFontInfo = createFontInfo("Regular", "Times New Roman", 14.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|s14.0/F");
		newFontInfo = createFontInfo("Regular", "Times New Roman", 12.0, Color.WHITE);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|c#ffffff/F");
		newFontInfo = createFontInfo("Bold", "Charis SIL", 12.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|b|fCharis SIL/F");
		newFontInfo = createFontInfo("Regular", "Times New Roman", 13.0, Color.BLACK);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|c#000000|s13.0/F");
		newFontInfo = createFontInfo("Italic", "Times New Roman", 16.0, Color.RED);
		checkRepresentrationThatDiffers(newFontInfo, oldFontInfo, "/f|i|s16.0/F");
	}

	private void checkRepresentrationThatDiffers(FontInfo newFontInfo, FontInfo oldFontInfo, String expected) {
		String rep = fiInserter.produceFontInfoRepresentationThatDiffers(newFontInfo, oldFontInfo);
		assertEquals(expected, rep);
	}

	@Test
	public void insertTest() {
		fiInserter = FontInfoInserter.getInstance();
		description = "(Word  (Infl[n] (Stem[n] (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")";
		fontInfo = createFontInfo("Bold", "Charis SIL", 10.0, Color.GREEN);
		String result = fiInserter.insert(fontInfo, description, 1, 24);
		String expected = "(Word  (Infl[n] (Stem[n]/f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
		// overwrite existing font info
		description = "(Word  (Infl[n] (Stem[n]/f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		fontInfo = createFontInfo("Regular", "CurlZ", 15.0, Color.MAROON);
		result = fiInserter.insert(fontInfo, description, 1, 24);
		expected = "(Word  (Infl[n] (Stem[n]/f|r|c#800000|fCurlZ|s15.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
		description = "(Word  (Infl[n] (Stem[n]     /f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		fontInfo = createFontInfo("Regular", "CurlZ", 15.0, Color.MAROON);
		result = fiInserter.insert(fontInfo, description, 1, 24);
		expected = "(Word  (Infl[n] (Stem[n]/f|r|c#800000|fCurlZ|s15.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
		description = "(Word  (Infl[n] (Stem[n]\t /f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		fontInfo = createFontInfo("Regular", "CurlZ", 15.0, Color.MAROON);
		result = fiInserter.insert(fontInfo, description, 1, 24);
		expected = "(Word  (Infl[n] (Stem[n]/f|r|c#800000|fCurlZ|s15.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
	}

	@Test
	public void removeTest() {
		fiInserter = FontInfoInserter.getInstance();
		description = "(Word  (Infl[n] (Stem[n]/f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		String result = fiInserter.remove(description, 1, 24);
		String expected = "(Word  (Infl[n] (Stem[n] (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
		description = "(Word  (Infl[n] (Stem[n]     /f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		result = fiInserter.remove(description, 1, 24);
		expected = "(Word  (Infl[n] (Stem[n] (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
		description = "(Word  (Infl[n] (Stem[n]\t /f|b|c#008000|fCharis SIL|s10.0/F (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		result = fiInserter.remove(description, 1, 24);
		expected = "(Word  (Infl[n] (Stem[n] (Infl[v] (Stem[v]\n"
				+ " (root (\\L  rika (\\G to.see))))(\\E בַ )\n"
				+ " (Aspect (\\L yka: (\\G  /aipfv/A ))) \n"
				+ "(Object (\\L  ma: (\\G /a1.obj/A)))) \n"
				+ "(Derv (\\L  na (\\G /anmlz/A))) )\n"
				+ " (Poss (\\L  yki (\\G  /a2.poss/A)))\n"
				+ "     (Case (\\L  paq (\\G  a-/apurp/A-bc-/aa/A))))\n"
				+ ")\n";
		assertEquals(expected, result);
	}
}
