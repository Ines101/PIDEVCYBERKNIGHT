package cyberknight.pidev.model;
import java.io.IOException;
import java.util.Date;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class AdsExcelExporter {

	  private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<Ads> listAds;
	     
	    public AdsExcelExporter(List<Ads> listAds) {
	        this.listAds = listAds;
	        workbook = new XSSFWorkbook();
	    }
	 
	 
	    private void writeHeaderLine() {
	        sheet = workbook.createSheet("listAds");
	         
	        Row row = sheet.createRow(0);
	         
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	         
	        createCell(row, 0, "Ads ID", style);      
	        createCell(row, 1, "Ads product Name", style);       
	        createCell(row, 2, "Start Date", style);    
	        createCell(row, 3, "End Date", style);
	        createCell(row, 4, "Status", style);
	        createCell(row, 5, "Banner", style);

	    }
	     
	    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        } else if (value instanceof Long) {
	            cell.setCellValue((Long) value);
	        }
	        else if (value instanceof Date) {
	            cell.setCellValue((Date) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	    }
	     
	    private void writeDataLines() {
	        int rowCount = 1;
	 
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	                 
	        for (Ads ads : listAds) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	             
	            createCell(row, columnCount++, ads.getId(), style);
	            createCell(row, columnCount++, ads.getProductname(), style);
	            createCell(row, columnCount++, ads.getStartdate(), style);
	            createCell(row, columnCount++, ads.getEnddate(), style);
	            createCell(row, columnCount++, ads.getStatus(), style);
	            createCell(row, columnCount++, ads.getBanner(), style);

	             
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws IOException {
	        writeHeaderLine();
	        writeDataLines();
	         
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	         
	        outputStream.close();
	         
	    } 
}
