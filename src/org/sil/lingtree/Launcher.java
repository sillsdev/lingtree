/**
 * Copyright (c) 2026 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree;

import java.awt.Desktop;
import java.awt.desktop.OpenFilesEvent;
import java.awt.desktop.OpenFilesHandler;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {
    	final String sOperatingSystem = System.getProperty("os.name");
    	MainApp mainApp = MainApp.getInstance();
    	if (sOperatingSystem.toLowerCase().contains("mac")) {
            // Register handler BEFORE launching JavaFX
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.setOpenFileHandler(new OpenFilesHandler() {
                    @Override
                    public void openFiles(OpenFilesEvent event) {
                        List<File> files = event.getFiles();
                        for (File file : files) {
                            // Pass file to your running app or store statically
                            mainApp.loadTreeData(file);
                        }
                    }
                });
            }
    	}
    	try {
			mainApp.performLaunch(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        Application.launch(MainApp.class, args);
    }
}   