// Copyright (c) 2016-2025 SIL Global 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 

package org.sil.lingtree.service;

public class DataMigrationException extends Exception {

	private static final long serialVersionUID = 1L;
	private String sFileName = "";
	
	public DataMigrationException(String filename) {
		sFileName = filename;
	}

	public String getFileName() {
		return sFileName;
	}

}
