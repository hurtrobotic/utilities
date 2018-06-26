package org.hurtrobotic.tess4J.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

public class TesseractTextUtils extends TesseractUtils {
	private static final String PROPDIR_LANG_DETECT = File.separatorChar +"profiles" + File.separatorChar + "com.cybozu.labs";
	private static final String PROP_MAPPING_ISO639 = File.separatorChar +"mapping_iso639.properties";
	private static final String PROP_SUPPORTED_LGPACK = File.separatorChar +"supported_lgpack_iso639.properties";
	private static Properties mappingIso639Part3;
	private static Properties supportedLgPack;

	static {
		 mappingIso639Part3 = loadPropertiesFromClasspath(PROP_MAPPING_ISO639);
		 supportedLgPack = loadPropertiesFromClasspath(PROP_SUPPORTED_LGPACK);
		 try {
			SingletonDetectorFactory.getInstance();
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String detectLanguage(String text) throws LangDetectException {	         
		String detectedLanguage = null;
		Detector detector = DetectorFactory.create(); 
        detector.append(text);
        ArrayList<Language> probabilities = detector.getProbabilities();
        if (probabilities != null) {
	        for(int i=0;i<probabilities.size();i++) {
	        	String lg = probabilities.get(i).lang;
	        	String isoLg = mappingIso639Part3.getProperty(lg);
	        	String check = supportedLgPack.getProperty(isoLg);
	        	if (check.equalsIgnoreCase("yes")) {
	        		detectedLanguage = isoLg;
	        		break;
	        	}
	        }
        }
        return detectedLanguage;
	}  	    
	
	private static class SingletonDetectorFactory {
		private SingletonDetectorFactory() throws LangDetectException {
			String confDir = System.getenv("NIFI_HOME") + File.separatorChar + "conf";
			String profileDir = confDir + PROPDIR_LANG_DETECT;
			DetectorFactory.loadProfile(profileDir);
		}

		private static class SingletonHolder {
			private static SingletonDetectorFactory sessionData = null;	
			
			private synchronized static SingletonDetectorFactory getSessionData() throws LangDetectException {
				if (sessionData == null) {
					sessionData = new SingletonDetectorFactory();
				}
				return sessionData;				
			}
		}

		public static SingletonDetectorFactory getInstance() throws LangDetectException {
			return TesseractTextUtils.SingletonDetectorFactory.SingletonHolder.getSessionData();
		}
	}	
}
