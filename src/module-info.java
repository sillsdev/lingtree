open module LingTree {
	// Exports
	exports org.sil.lingtree;

	// Java
	requires java.desktop;
	requires java.prefs;

	// JavaFX
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.swing;
	requires javafx.web;

	// JAXB
	requires jakarta.xml.bind;
	requires jakarta.activation;

	// JUnit
	requires junit;

	// Other modules/libraries
	requires antlr;
	requires controlsfx;
	requires transitive libjavadev;
	requires richtextfx.fat;
}