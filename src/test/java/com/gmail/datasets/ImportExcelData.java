package com.gmail.datasets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelData {

	private static List<GmailUser> readExcel(String filePath, String fileName, String sheetName) throws IOException {

		List<GmailUser> users = new ArrayList<GmailUser>();

		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook loginData = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class
			loginData = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class
			loginData = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name
		Sheet sheet = loginData.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount + 1; i++) {

			Row row = sheet.getRow(i);
			users.add(new GmailUser(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue()));
		}

		return users;
	}

	public static List<GmailUser> getUsersFromExcel() {
		ImportExcelData objExcelFile = new ImportExcelData();

		// Prepare the path of excel file
		String filePath = "C:\\Data";

		// Call read file method of the class to read data

		try {
			return objExcelFile.readExcel(filePath, "LoginData.xlsx", "Sheet1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<GmailUser>();
	}

	// Main function is calling readExcel function to read data from excel file

	public static void main(String... strings) throws IOException {

		List<GmailUser> users = getUsersFromExcel();
		for (GmailUser user : users) {
			System.out.println(user.username + "--" + user.password);
		}

	}

}
