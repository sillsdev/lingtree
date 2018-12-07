/**
 * Copyright (c) 2016-2018 SIL International
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
package org.sil.lingtree.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.application.Platform;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sil.lingtree.Constants;
import org.sil.utility.view.JavaFXThreadingRule;

public class BatchTreeHandlerTest {
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	BatchTreeHandler handler;
	String pngFilePath;
	String svgFilePath;

	@Before
	public void setUp() throws Exception {
		pngFilePath = Constants.UNIT_TEST_DATA_FILE_NAME + "png";
		svgFilePath = Constants.UNIT_TEST_DATA_FILE_NAME + "svg";
		deleteGraphicFiles();
	}

	private void deleteGraphicFiles() throws IOException {
		File file = new File(pngFilePath);
		Files.deleteIfExists(file.toPath().toAbsolutePath());
		file = new File(svgFilePath);
		Files.deleteIfExists(file.toPath().toAbsolutePath());
	}

	@After
	public void tearDown() throws Exception {
		// we use runLater to allow time for the drawer to work
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					deleteGraphicFiles();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Test
	public void processAsBatchTest() throws IOException {
		// file missing
		handler = new BatchTreeHandler(Constants.UNIT_TEST_DATA_FILE + ".bad", null);
		assertFalse(handler.fileExists());
		handler.processTree();
		// file is good; tree description is ill-formed
		handler = new BatchTreeHandler(Constants.UNIT_TEST_DATA_FILE_BAD_TREE, null);
		assertTrue(handler.fileExists());
		handler.processTree();
		File file = new File(svgFilePath);
		assertFalse(file.exists());
		file = new File(pngFilePath);
		assertFalse(file.exists());
		// file is good; tree description is well-formed
		handler = new BatchTreeHandler(Constants.UNIT_TEST_DATA_FILE, null);
		assertTrue(handler.fileExists());
		handler.processTree();
		file = new File(svgFilePath);
		assertTrue(file.exists());
		// we use runLater to allow time for the drawer to work
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				File file = new File(pngFilePath);
				assertTrue(file.exists());
			}
		});
	}
}
