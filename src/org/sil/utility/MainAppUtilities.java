/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility;

import java.io.File;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Abstract class to extend from for a MainApp class in an application It allows
 * the use of the libjavadev utilities.
 * 
 * @author Andy Black
 *
 */
public interface MainAppUtilities {

	public abstract ApplicationPreferencesUtilities getApplicationPreferences();

	public abstract Image getNewMainIconImage();

	public abstract Stage getPrimaryStage();

	public abstract void saveFile(File file);

	public abstract void updateStageTitle(File file);
}
