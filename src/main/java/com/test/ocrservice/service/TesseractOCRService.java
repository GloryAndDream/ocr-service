package com.test.ocrservice.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TesseractOCRService implements OCRService {

	@Value("${tesseract.datapath}")
	String tessDataPath;

	@Override
	public String processImg(File img) throws Exception {

		String text = OcrUtil.doOCR(img, tessDataPath);

		return text;
	}

}
