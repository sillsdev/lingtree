module org.sil.lingtree {
	// Exports
	exports org.sil.lingtree;
	exports org.sil.lingtree.backendprovider;
	exports org.sil.lingtree.model;
	exports org.sil.lingtree.descriptionparser;
	exports org.sil.lingtree.descriptionparser.antlr4generated;
	exports org.sil.lingtree.service;
	exports org.sil.lingtree.view;

	opens org.sil.lingtree.view to javafx.fxml;
//	opens org.sil.lingtree.view.fxml to richtextfx.fat;

	// Java
	requires java.desktop;
	requires java.prefs;

	// JavaFX
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires javafx.graphics;
	requires javafx.swing;
	requires javafx.web;

	// JAXB
	requires jakarta.xml.bind;
	requires jakarta.activation;
	opens org.sil.lingtree.model;

	// JNA
	requires com.sun.jna;
	requires com.sun.jna.platform;

	// LibJavaDev
	requires transitive org.sil.utility;
//	requires org.sil.utility.service;
//	requires org.sil.utility.service.keyboards;
//	requires org.sil.utility.view;
//	requires org.sil.utility.xml;

	// JUnit
	requires junit;

	// Other modules/libraries
	requires antlr;
//	requires transitive libjavadev;
	requires transitive org.controlsfx.controls;
	requires javafx.base;
	requires javafx.media;
	requires java.base;
	requires transitive richtextfx.fat;
	requires json.simple;
//	requires org.sil.utility;
}
