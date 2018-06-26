package org.hurtrobotic.tess4J.utilities;

import java.io.File;
import java.io.IOException;

import org.hurtrobotic.tess4J.PdfInfo;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfUtils {
	public static PdfInfo getPdfInfoType(File theFile) throws IOException {
		int g = 0;
		int gg = 0;
		PDDocument doc = PDDocument.load(theFile);

		gg = doc.getNumberOfPages();
		for (PDPage page : doc.getPages()) {
			PDResources resource = page.getResources();
			for (COSName xObjectName : resource.getXObjectNames()) {
				PDXObject xObject = resource.getXObject(xObjectName);
				if (xObject instanceof PDImageXObject) {
					((PDImageXObject) xObject).getImage();
					g++;
				}
			}
		}
		doc.close();
		if (g == gg) 
		{
			return PdfInfo.IMAGE;
		} else {
			return PdfInfo.SEARCHABLE;
		}

	}
}
