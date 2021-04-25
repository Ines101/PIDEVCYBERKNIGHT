package cyberknight.pidev.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cyberknight.pidev.model.Event;



public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "id_e", "Name_e", "Location_e", "Date_e","Max_participants_e" };
	  static String SHEET = "Events";

	  public static ByteArrayInputStream productsToExcel(List<Event> events) {

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 1;
	      for (Event e : events) {
	        Row row = sheet.createRow(rowIdx++);

	        row.createCell(0).setCellValue(e.getId_e());
	        row.createCell(1).setCellValue(e.getName_e());
	        row.createCell(2).setCellValue(e.getLocation_e());
	        row.createCell(3).setCellValue(e.getDate_e());
	        row.createCell(4).setCellValue(e.getMax_participants_e());
	 
	        
	      }

	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }

}
