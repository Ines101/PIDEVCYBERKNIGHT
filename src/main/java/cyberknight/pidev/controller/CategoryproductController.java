package cyberknight.pidev.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import cyberknight.pidev.model.CategoryPDFExporter;
import cyberknight.pidev.model.Categoryproduct;
import cyberknight.pidev.service.CategoryproductService;
import cyberknight.pidev.service.ProductService;

import org.springframework.http.ResponseEntity;  

@RestController
@RequestMapping("/categorie")
public class CategoryproductController {

	@Autowired
	CategoryproductService CategoryService;
	ProductService productService;

	
	
	
	
	  @GetMapping("/Categoryproduct/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=CategoryService_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue); 
	         
	        List<Categoryproduct> listCategories = CategoryService.getAllCategory();
	         
	        CategoryPDFExporter exporter = new CategoryPDFExporter(listCategories);
	        exporter.export(response);
	         
	    }
	// Affichage all products
	@RequestMapping(value = "/DisplayAllCategory", method = RequestMethod.GET)
	public List<Categoryproduct> getAll() {
		return CategoryService.getAllCategory();
	}
	
   
	
	@RequestMapping(value = "/AddCategory", method = RequestMethod.POST)
	public HttpStatus addCategory(@RequestBody Categoryproduct Categoryproduct) {
		return CategoryService.addCategory(Categoryproduct) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/DeleteCategory/{id}", method = RequestMethod.DELETE)
	public HttpStatus deleteCategory(@PathVariable Long id) {
		return CategoryService.deleteCategory(id) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;

	}

	@PutMapping(value = "/ModifyCategory")
	public HttpStatus updateCategory(@RequestBody Categoryproduct Categoryproduct) {
		return CategoryService.updateCategory(Categoryproduct) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
	}
	/*
	@PutMapping("/ModifyCategoryy/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Categoryproduct student, @PathVariable long id) {

		Optional<Categoryproduct> CategoryOptional = Category.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		Categoryproduct.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}
*/
}
