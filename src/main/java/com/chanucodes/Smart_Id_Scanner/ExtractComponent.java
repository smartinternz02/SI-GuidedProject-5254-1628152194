package com.chanucodes.Smart_Id_Scanner;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Component()
public class ExtractComponent {

    Logger logger = LoggerFactory.getLogger(ExtractComponent.class);

    public String extractFromPng(String directory) {
	File[] files = getFiles(directory);
	return processFiles(files[0]);
    }

    private File[] getFiles(String directory) {
	File dir = new File(directory);
	File[] files = dir.listFiles();
	return files;
    }

    private String processFiles(File file) {
	logger.info(file.getAbsolutePath());

	ITesseract tesseract = new Tesseract();
	tesseract.setDatapath("tessdata");
	tesseract.setLanguage("eng");

	String result = "";
	try {
	    result = tesseract.doOCR(file);
	} catch (TesseractException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Error occurred running OCR on file");
	}
	return result;
    }
}
