// Copyright (c) 2018 SIL International 
// This software is licensed under the LGPL, version 2.1 or later 
// (http://www.gnu.org/licenses/lgpl-2.1.html) 
/**
 * 
 */
package org.sil.lingtree.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.sil.lingtree.Constants;
import org.sil.utility.HandleExceptionMessage;
import org.sil.utility.XsltParameter;
import org.w3c.dom.Document;

/**
 * @author Andy Black
 *
 */
public class DatabaseMigrator {

	File databaseFile;
	double dpi = 96;

	public DatabaseMigrator(File dataFile) {
		this.databaseFile = dataFile;
	}

	public File getDatabaseFile() {
		return databaseFile;
	}

	public void setDatabaseFile(File databaseFile) {
		this.databaseFile = databaseFile;
	}

	public double getDpi() {
		return dpi;
	}

	public void setDpi(double d) {
		this.dpi = d;
	}

	public int getVersion() {
		int version = -1;
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(new FileInputStream(databaseFile),
					Constants.UTF8_ENCODING);
			BufferedReader bufr = new BufferedReader(reader);
			String line = bufr.readLine();
			while (line != null && !line.contains("<lingTreeTree") && !line.contains("<LingTreeTree")) {
				line = bufr.readLine();
			}
			int i = line.indexOf("dbversion=");
			if (i == -1) {
				// it's the first version which did not have a version
				// number in the root element
				version = 1;
			} else {
				String s = line.substring(i + 11);
				int iEnd = s.indexOf("\"");
				if (iEnd > -1) {
					version = Integer.parseInt(s.substring(0, iEnd));
				}
			}
			bufr.close();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}

	public void doMigration() {
		try {
			int version = getVersion();
			// make a backup of the database file just in case
			String sFile = databaseFile.getPath();
			int i = sFile.lastIndexOf(Constants.LINGTREE_DATA_FILE_EXTENSION);
			String sBackupFileName = sFile.substring(0, i) + "bak";
			Files.copy(Paths.get(databaseFile.getPath()), Paths.get(sBackupFileName),
					StandardCopyOption.REPLACE_EXISTING);

			File file = databaseFile;
			while (version < Constants.CURRENT_DATABASE_VERSION) {
				List<XsltParameter> params = new ArrayList<XsltParameter>();
				if (version == 1) {
					params.add(new XsltParameter("dpi", dpi));
				}
				file = applyMigrationTransformToFile(version, file, params);
				if (version == 1) {
					file = convertArgbToHexNotation(file);
				}
				version++;
			}
			Files.copy(Paths.get(file.getPath()), Paths.get(databaseFile.getPath()),
					StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
			// TODO: delete the temp files
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File applyMigrationTransformToFile(int version, File file, List<XsltParameter> params) {
		String stylesheet = Constants.MIGRATION_XSLT_FILE_NAME + version + ".xsl";
		File tempSaveFile = null;
		try {
			tempSaveFile = File.createTempFile("LingTreeDataMigration" + version, ".tre");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			File xslt = new File(stylesheet);
			if (!xslt.exists()) {
				throw new DataMigrationException(xslt.getPath());
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			StreamSource stylesource = new StreamSource(stylesheet);
			Transformer transformer = tFactory.newTransformer(stylesource);
			if (params.size() > 0) {
				for (XsltParameter param : params) {
					transformer.setParameter(param.name, param.value);
				}
			}

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(tempSaveFile);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
			HandleExceptionMessage.show("Migration Error", "Failed to transform", e.getMessage(),
					true);
		}
		return tempSaveFile;
	}

	private File convertArgbToHexNotation(File version2File) {
		File fileWithHexColors = null;
		try {
			String sConverted = new String(Files.readAllBytes(version2File.toPath()),
					StandardCharsets.UTF_8);
			// change it
			StringBuilder sb = new StringBuilder();
			int lastIndex = convertColorToHex(sConverted, "lineColor", 0, sb);
			lastIndex = convertColorToHex(sConverted.substring(lastIndex), "backgroundColor", lastIndex, sb);
			lastIndex = convertColorToHex(sConverted.substring(lastIndex), "color", lastIndex, sb);
			lastIndex = convertColorToHex(sConverted.substring(lastIndex), "color", lastIndex, sb);
			lastIndex = convertColorToHex(sConverted.substring(lastIndex), "color", lastIndex, sb);
			lastIndex = convertColorToHex(sConverted.substring(lastIndex), "color", lastIndex, sb);
			sb.append(sConverted.substring(lastIndex));
			
			fileWithHexColors = saveFileWithHexColors(version2File, sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileWithHexColors;
	}

	private int convertColorToHex(String sRest, String sElementName, int lastIndex,
			StringBuilder sb) {
		int iBeg = sRest.indexOf("<" + sElementName + ">") + sElementName.length() + 2;
		int iEnd = sRest.substring(iBeg).indexOf("</" + sElementName + ">") + iBeg;
		sb.append(sRest.substring(0, iBeg));
		String sArgb = sRest.substring(iBeg, iEnd);
		int argb = Integer.parseInt(sArgb);
		String sHex = Integer.toHexString(argb);
		sb.append("#");
		sb.append(sHex.substring(2));
		lastIndex = lastIndex + iEnd;
		return lastIndex;
	}

	private File saveFileWithHexColors(File version2File, String sConverted) throws IOException {
		File fileWithHexColors;
		String path = version2File.getPath();
		int i = path.lastIndexOf(".tre");
		fileWithHexColors = File.createTempFile(path.substring(0, i) + "HexColors", ".tre");
		Files.write(fileWithHexColors.toPath(), sConverted.getBytes(), StandardOpenOption.WRITE);
		return fileWithHexColors;
	}
}
