/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.utility;

/**
 * Abstract class to extend from for an ApplicationPreferences class in an application
 * It allows the use of the libjavadev utilities.
 *  
 * @author Andy Black
 *
 */
public abstract class ApplicationPreferencesUtilities {
	
	public abstract String getLastOpenedDirectoryPath();
	public abstract void setLastOpenedDirectoryPath(String path);
}
