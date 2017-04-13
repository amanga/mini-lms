package com.bunge.lms.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetHelper {

	public static Workbook getWorkbook(String excelFilePath)throws Exception{
		Workbook workbook = null;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		if(excelFilePath.endsWith(".xlsx")){
			workbook = new XSSFWorkbook(inputStream);
		}else if(excelFilePath.endsWith(".xlsx")){
			workbook = new HSSFWorkbook(inputStream);
		}else {
			throw new IllegalArgumentException("The specified file is not excel file.");
		}
		return workbook;
	}
}
