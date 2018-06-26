package org.hurtrobotic.tess4J;

public enum PdfInfo {
	SEARCHABLE("searchable"), 
	IMAGE("image");
	
	private String type;
	
	PdfInfo(String type) {
		this.type = type;
	}
	
	public String type() {
		return type;
	}
	
}
