/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */

package org.sil.lingtree.service;

/**
 * 
 */
public class FontInfoParserException extends Exception {

	private static final long serialVersionUID = 2L;
	protected int lineNumberOfError;
	protected int characterPositionInLineOfError;
	protected String msg;

	public FontInfoParserException(int lineNumber, int charPos, String msg) {
		lineNumberOfError = lineNumber;
		characterPositionInLineOfError = charPos;
		this.msg = msg;
	}

	public int getLineNumberOfError() {
		return lineNumberOfError;
	}

	public void setLineNumberOfError(int lineNumberOfError) {
		this.lineNumberOfError = lineNumberOfError;
	}

	public int getCharacterPositionInLineOfError() {
		return characterPositionInLineOfError;
	}

	public void setCharacterPositionInLineOfError(int errorPos) {
		this.characterPositionInLineOfError = errorPos;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
