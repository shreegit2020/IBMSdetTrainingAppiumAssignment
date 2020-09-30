package DW;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.common.collect.Table.Cell;



public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		

		String path = "C:\\Users\\AnkurSaxena\\Desktop\\SDET\\test.xls";
		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = new HSSFWorkbook(fis);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		String[] value = new String[3];
		for(int i=1; i<=lastRow; i++){
			int a=0;
		Row row = sheet.getRow(i);
		int lastCell = row.getLastCellNum();
		for(int j=0; j<lastCell; j++){
			
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);
		value[j] = cell.getStringCellValue();
		a=a+1;
		}
		System.out.println(value[0]);
		System.out.println(value[1]);
		System.out.println();
		}
		
		}
}
