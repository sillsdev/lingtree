// Copyright (c) 2016 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */

package org.sil.lingtree.view;

import javax.xml.bind.annotation.*;

import org.controlsfx.dialog.FontSelectorDialog;
import org.sil.lingtree.model.FontInfo;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Andy Black
 *
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class FontInfoController {

	private FontInfo fontInfo;

	public FontInfoController(FontInfo fontInfo) {
		super();
		this.fontInfo = fontInfo;
	}

	public FontInfoController() {
		fontInfo =new FontInfo("System", 12.0, "Regular");
	}

	public void handleFont(Stage stage) {
		 FontSelectorDialog dlg = new FontSelectorDialog(fontInfo.getFont());
         dlg.initOwner(stage);
         //dlg.setResult(languageProject.getVernacularFont());
         dlg.showAndWait();
         Font chosenFont = dlg.getResult();
         if (chosenFont != null) {
        	 this.fontInfo = new FontInfo(chosenFont.getFamily(), chosenFont.getSize(), chosenFont.getStyle());
         }
	}
	
	// Eclipse Luna 4.4 wants the suppress warnings
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void handleColor(Stage stage) {
		 final ColorPicker colorPicker = new ColorPicker();

	        colorPicker.setOnAction(new EventHandler(){
	 
	            @Override
	            public void handle(Event event) {
	                fontInfo.setColor(colorPicker.getValue());
	            }
	        });
	}
}
