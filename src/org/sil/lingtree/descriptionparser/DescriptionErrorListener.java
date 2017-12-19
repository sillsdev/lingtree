// Copyright (c) 2016-2017 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * @author Andy Black
 * 
 */
package org.sil.lingtree.descriptionparser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.*;

public class DescriptionErrorListener {
	
	public static class VerboseListener extends BaseErrorListener {
		DescriptionErrorListener listener = new DescriptionErrorListener();
	    LinkedList<DescriptionErrorInfo> errorMessages = new LinkedList<DescriptionErrorInfo>(
				Arrays.asList(new DescriptionErrorInfo()));
		
	    @Override
	    public void syntaxError(Recognizer<?, ?> recognizer,
	                            Object offendingSymbol,
	                            int line, int charPositionInLine,
	                            String msg,
	                            RecognitionException e)
	    {
	        DescriptionErrorInfo info = new DescriptionErrorInfo(offendingSymbol, line, charPositionInLine, msg, e);
	        errorMessages.add(info);
	    }

		public List<DescriptionErrorInfo> getErrorMessages() {
			return errorMessages;
		}
		
		public void clearErrorMessageList() {
			errorMessages.clear();
		}

	}

}
