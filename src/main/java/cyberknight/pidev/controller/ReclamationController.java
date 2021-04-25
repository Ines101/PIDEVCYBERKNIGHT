package cyberknight.pidev.controller;

import static org.springframework.http.HttpStatus.OK;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cyberknight.pidev.dto.reclamationRequest;
import cyberknight.pidev.model.reclamation;
import cyberknight.pidev.service.RoleService;
import cyberknight.pidev.service.reclamtionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reclamation")
@AllArgsConstructor
public class ReclamationController {
	private final reclamtionService reclamtionService;

	@PostMapping("/addReclamation")
	public ResponseEntity<String> addReclamation(@RequestBody reclamationRequest reclamationRequest) {

		reclamtionService.createReclamation(reclamationRequest);
		return new ResponseEntity<>("Reclamation Created : " + reclamationRequest.getSubject(), OK);
	}

	@PostMapping("/deleteReclamation")
	public ResponseEntity<String> deleteReclamation(@RequestBody reclamationRequest reclamationRequest) {
		reclamtionService.deleteReclamation(reclamationRequest);
		return new ResponseEntity<>("Reclamation deleted", OK);
	}

	@GetMapping("/editReclamation")
	public ResponseEntity<String> editReclamtion(@RequestBody reclamationRequest reclamationRequest) {
		reclamtionService.editReclamation(reclamationRequest);
		return new ResponseEntity<>("Reclamation Edited : " + reclamationRequest.getSubject(), OK);
	}

	// kol user w mta3ou mazelt// DONE
	@GetMapping("/showReclamation")
	public List<reclamation> getAllReclamations() {
		return reclamtionService.getAllReclamations();
	}

	@PostMapping("/fetchBySubject")
	public String fetchBySubject(@RequestBody reclamationRequest reclamationRequest) {
		List<reclamation> recs = new ArrayList<>();
		recs = reclamtionService.fetchBySubject(reclamationRequest);
		if (recs.isEmpty())
			return "Nothing Found";
		return recs.toString();
	}

	// @GetMapping("/PDFReclamation")
	// public String PdfReclamation() throws Exception {
	//
	// // PDDocument document = new PDDocument();
	// // PDPage page = new PDPage();
	// // document.addPage(page);
	// // String text = reclamtionService.getAllReclamations().toString();
	// //
	// // PDPageContentStream contentStream = new PDPageContentStream(document,
	// // page);
	// // PDFont font = PDType1Font.HELVETICA_BOLD;
	// // Point2D.Float center = new Point2D.Float(0, 0);
	// PDDocument doc = null;
	// doc = new PDDocument();
	// PDPage page = new PDPage();
	// doc.addPage(page);
	// PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	//
	// PDFont pdfFont = PDType1Font.HELVETICA;
	// float fontSize = 10;
	// float leading = 1.5f * fontSize;
	//
	// PDRectangle mediabox = page.getMediaBox();
	// float margin = 72;
	// float width = mediabox.getWidth() - 2 * margin;
	// float startX = mediabox.getLowerLeftX() + margin;
	// float startY = mediabox.getUpperRightY() - margin;
	//
	// String text = reclamtionService.getAllReclamations().toString();
	// List<String> lines = new ArrayList<String>();
	// int lastSpace = -1;
	// while (text.length() > 0) {
	// int spaceIndex = text.indexOf(' ', lastSpace + 1);
	// if (spaceIndex < 0)
	// spaceIndex = text.length();
	// String subString = text.substring(0, spaceIndex);
	// float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
	// // System.out.printf("'%s' - %f of %f\n", subString, size, width);
	// if (size > width) {
	// if (lastSpace < 0)
	// lastSpace = spaceIndex;
	// subString = text.substring(0, lastSpace);
	// lines.add(subString);
	// text = text.substring(lastSpace).trim();
	// // System.out.printf("'%s' is line\n", subString);
	// lastSpace = -1;
	// } else if (spaceIndex == text.length()) {
	// lines.add(text);
	// // System.out.printf("'%s' is line\n", text);
	// text = "";
	// } else {
	// lastSpace = spaceIndex;
	// }
	// }
	//
	// contentStream.beginText();
	// contentStream.setFont(pdfFont, fontSize);
	// contentStream.newLineAtOffset(startX, startY);
	// for (String line : lines) {
	// contentStream.showText(line);
	// contentStream.newLineAtOffset(0, -leading);
	// }
	// contentStream.endText();
	// contentStream.close();
	//
	// doc.save("reclamation.pdf");
	// doc.close();
	//
	// return "Pdf created in project folder";
	// }

}
