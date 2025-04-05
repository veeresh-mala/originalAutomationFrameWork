package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\vTigerTestData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		String lastname = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		System.out.println(lastname);
		
		String orgname = wb.getSheet("Contacts").getRow(4).getCell(3).toString();
		System.out.println(orgname);
		
		String industryType = wb.getSheet("Organization").getRow(4).getCell(3).toString();
		System.out.println(industryType);
		
		String type = wb.getSheet("Organization").getRow(7).getCell(3).toString();
		System.out.println(type);
		
		String orgName = wb.getSheet("Organization").getRow(1).getCell(2).toString();
		System.out.println(orgName);


	}
}
