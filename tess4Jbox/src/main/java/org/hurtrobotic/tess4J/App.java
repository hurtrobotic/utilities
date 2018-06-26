package org.hurtrobotic.tess4J;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.ITesseract.RenderedFormat;
import net.sourceforge.tess4j.Tesseract;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ITesseract instance = new Tesseract();
		instance.setLanguage("eng");
		instance.setDatapath(args[0]);
		instance.setPageSegMode(3);
		instance.setOcrEngineMode(2);

		BufferedImage imBuff;
		try {
			// imBuff = ImageIO.read(new File(args[1]));

			System.out.println("Start processing OCR.");
			// String txt = instance.doOCR(imBuff);
			List<RenderedFormat> lrdf = new ArrayList<RenderedFormat>(
					Arrays.asList(RenderedFormat.PDF, RenderedFormat.TEXT));
			instance.createDocuments(args[1], args[1], lrdf);
			System.out.println("End processing OCR.");

			// System.out.println(txt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}