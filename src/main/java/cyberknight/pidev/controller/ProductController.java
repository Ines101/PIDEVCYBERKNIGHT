package cyberknight.pidev.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import cyberknight.pidev.model.Product;
import cyberknight.pidev.model.ProductPDFExporter;
import cyberknight.pidev.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	
	  @GetMapping("/export/pdf")
	    public void exportToPDFproduct(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=ProductService_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue); 
	         
	        List<Product> listproduct = productService.getAllProducts();
	         
	        ProductPDFExporter exporter = new ProductPDFExporter(listproduct);
	        exporter.export(response);
	         
	    }
	// Affichage all products
	@RequestMapping(value = "/DisplayAllproduct", method = RequestMethod.GET)
	public List<Product> getAll() {
		return productService.getAllProducts();
	}
	

	// Recherche by name
	@RequestMapping(value = "/SearchProductByName/{name}", method = RequestMethod.GET)
	public List<Product> getAllProducts(@PathVariable String name) {
		return productService.findByName(name);
	}
	
	
	// Recherche by barcode
	@RequestMapping(value = "/SearchProductByBarcode/{barcode}", method = RequestMethod.GET)
	public List<Product> getAllByBarcode(@PathVariable String barcode) {
		return productService.findByBarcode(barcode);
	}
	

	@RequestMapping(value = "/Addproduct", method = RequestMethod.POST)
	public HttpStatus AddProduct(@RequestBody Product product) {
		return productService.addProduct(product) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

	@RequestMapping(value = "/Deleteproduct/{id}", method = RequestMethod.DELETE)
	public HttpStatus deleteProduct(@PathVariable Long id) throws Exception {
		return productService.deleteProduct(id) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;

	}

	@RequestMapping(value = "/Modifyproduct", method = RequestMethod.PUT)
	public HttpStatus updateProduct(@RequestBody Product product) throws Exception {
		return productService.updateProduct(product) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
	}
	
	@RequestMapping(value = "/AddLike", method = RequestMethod.PUT)
	public HttpStatus Addlike(@RequestBody Product product) throws Exception {
		return productService.Addlikes(product) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
	}
	
	@RequestMapping(value = "/Dislike", method = RequestMethod.PUT)
	public HttpStatus Dislike(@RequestBody Product product) throws Exception {
		return productService.Dislike(product) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
	}

}
