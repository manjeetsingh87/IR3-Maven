package com.ir3.service.dao;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class LanguageDetector {

	Detector detector;
	String profilePath = "//home//sanjay//Project//IR//partb_data//JSONIC//profiles";

	public LanguageDetector() {
		try {
			DetectorFactory.loadProfile(profilePath);
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String langDetect(String text){
		String langDetected = "";
		try {
			detector = DetectorFactory.create();
			detector.append(text);
			langDetected = detector.detect();
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return langDetected;

	}

}
