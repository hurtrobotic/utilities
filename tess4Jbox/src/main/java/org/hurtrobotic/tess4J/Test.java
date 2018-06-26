package org.hurtrobotic.tess4J;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

public class Test {

	public Test(String[] args) throws LangDetectException, IOException {
		Collection<File> theFiles = listFileTree(new File(args[0]));
		Iterator<File> it = theFiles.iterator();
		while(it.hasNext()) {
			File current = it.next();
			System.out.print(">> Current file : " + current.getName() + " : ");
			System.out.println(testPdf(current));
		}
	}

	public static Collection<File> listFileTree(File dir) {
		Set<File> fileTree = new HashSet<File>();
		if (dir == null || dir.listFiles() == null) {
			return fileTree;
		}
		for (File entry : dir.listFiles()) {
			if (entry.isFile())
				fileTree.add(entry);
			else
				fileTree.addAll(listFileTree(entry));
		}
		return fileTree;
	}

	public static String testPdf(File theFile) throws IOException {
		String s = "";
		int g = 0;
		int gg = 0;
		PDDocument doc = PDDocument.load(theFile);

		gg = doc.getNumberOfPages();
		int i=1;
		for (PDPage page : doc.getPages()) {
			PDResources resource = page.getResources();
			//System.out.println("Page : " + i +" >> ");
			for (COSName xObjectName : resource.getXObjectNames()) {
				//System.out.print(xObjectName);
				PDXObject xObject = resource.getXObject(xObjectName);
				//System.out.println(">> ");
				if (xObject instanceof PDImageXObject) {
					((PDImageXObject) xObject).getImage();
					g++;
				}

			}
			i++;

		}
		doc.close();
		if (g == gg) // pdf pages if equal to the images
		{
			return "Scanned pdf";
		} else {
			return "Searchable pdf";
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Test(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(String profileDirectory) throws LangDetectException {
		DetectorFactory.loadProfile(profileDirectory);
	}

	public String detect(String text) throws LangDetectException {
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.detect();
	}

	public ArrayList<Language> detectLangs(String text) throws LangDetectException {
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.getProbabilities();
	}

	private static String[] getFileInfos(String fileName) {
		String[] retour = null;
		final Pattern pattern = Pattern.compile("^(.*)\\.([^.]*)$");
		if (!StringUtils.isEmpty(fileName)) {
			Matcher matcher = pattern.matcher(fileName);
			if (matcher.find()) {
				if (matcher.groupCount() == 2) {
					retour = new String[] { matcher.group(1).trim(), matcher.group(2).trim() };
				}
			}
		}
		return retour;
	}

	private String trimFileExtension(String fileName) {
		return fileName.replaceAll("\\.[^.]*$", "");
	}

}
