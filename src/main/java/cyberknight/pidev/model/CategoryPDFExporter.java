package cyberknight.pidev.model;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class CategoryPDFExporter {
	
	private List<Categoryproduct> listCategoryproduct;

	public CategoryPDFExporter(List<Categoryproduct> listCategoryproduct) {
        this.listCategoryproduct = listCategoryproduct;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(2);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("idCategory", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Categoryname", font));
        table.addCell(cell);
         
           
    }
     
    private void writeTableData(PdfPTable table) {
        for (Categoryproduct Categoryproduct : listCategoryproduct) {
            table.addCell(String.valueOf(Categoryproduct.getId()));
            table.addCell(Categoryproduct.getCategoryname());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of CategoryProducts", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}

