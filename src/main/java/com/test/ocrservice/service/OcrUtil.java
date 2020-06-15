package com.test.ocrservice.service;

import java.io.File;
import java.io.FileNotFoundException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrUtil {

	private final static String CHINESE = "chi_sim";
	private final static String ENGLISH = "eng";

	private static ITesseract instance;

	public static String doOCR(File file, String tessData) throws TesseractException, FileNotFoundException {
		return doOCR(file, tessData, ENGLISH);
	}

	/* 过程非常简单 */
	public static String doOCR(File file, String tessData, String language)
			throws TesseractException, FileNotFoundException {

		ITesseract instance = getTesseract();
		if (!file.isFile()) {
			throw new FileNotFoundException(file.getAbsolutePath() + " Not found");
		}

		instance.setDatapath(tessData);
		instance.setLanguage(language);
		String result = instance.doOCR(file);
		return result;
	}

	private static ITesseract getTesseract() {
		if (instance == null) {
			instance = new Tesseract();
		}
		return instance;
	}
}