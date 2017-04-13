package com.bunge.lms.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetHelper {

	public static Workbook getWorkbook(String excelFilePath)throws Exception{
		Workbook workbook = null;
		FileInputStream inputStream = null;
		if(excelFilePath.endsWith(".xlsx")){
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
			inputStream.close();
		}else if(excelFilePath.endsWith(".xlsx")){
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new HSSFWorkbook(inputStream);
			inputStream.close();
		}else {
			throw new IllegalArgumentException("The specified file is not excel file.");
		}
		return workbook;
	}
	
	public static Workbook getWorkbook(FileInputStream fileInputStream, String fileType)throws Exception{
		Workbook workbook = null;		
		if(fileType.endsWith(".xlsx")){
			workbook = new XSSFWorkbook(fileInputStream);
		}else if(fileType.endsWith(".xlsx")){
			workbook = new HSSFWorkbook(fileInputStream);
		}else {
			throw new IllegalArgumentException("The specified file is not excel file.");
		}
		return workbook;
	}
}
