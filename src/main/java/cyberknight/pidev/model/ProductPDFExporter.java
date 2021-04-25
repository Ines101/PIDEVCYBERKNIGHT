package cyberknight.pidev.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ProductPDFExporter {
	
	private List<Product> listproduct;

	public ProductPDFExporter(List<Product> listproduct) {
        this.listproduct = listproduct;
        
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(3);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
   
         
        cell.setPhrase(new Phrase("name", font));
        table.addCell(cell);
  
        cell.setPhrase(new Phrase("barcode", font));
        table.addCell(cell);
        /*   
        cell.setPhrase(new Phrase("name", font));
        table.addCell(cell);
/*        /* 
        cell.setPhrase(new Phrase("stock", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("price", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("rating", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Likes", font));
        table.addCell(cell); */
        
       
       
        /*cell.setPhrase(new Phrase("sss", font));
        table.addCell(cell);*/
       

       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Product product : listproduct) {
         //  table.addCell(String.valueOf(product.getId()));
          table.addCell(product.getBarcode());
            table.addCell(product.getName());
            /*  table.addCell(String.valueOf(product.getLikes()));*/
            /* table.addCell(product.getMateriel());*/
           /* table.addCell(String.valueOf(product.getPrice()));
            table.addCell(String.valueOf(product.getRating()));
            table.addCell(String.valueOf(product.getStock()));*/
            //table.addCell(String.valueOf(product.getCategory()));
         
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.orange);
         
        Paragraph p = new Paragraph("List of Products", font);
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
