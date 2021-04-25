package cyberknight.pidev.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cyberknight.pidev.model.Product;
import cyberknight.pidev.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
	ProductRepository<Product> productRepository;

	@Transactional
	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	@Transactional
	public List<Product> findByBarcode(String barcode) {
		return productRepository.findByBarcode(barcode);
	}
	


	@Transactional
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll(Sort.by("name").ascending());
	}

	@Transactional
	public boolean deleteProduct(Long productId) throws Exception {
		Optional<Product> Optionalproduct =	productRepository.findById(productId);
		Optionalproduct.orElseThrow(() -> new Exception("Product not found To delete"));
		productRepository.deleteById(productId);
		return !productRepository.existsById(productId);

	}

	@Transactional
	public boolean addProduct(Product product) {
		return productRepository.save(product) != null;
	}

	@Transactional
	public boolean updateProduct(Product product) throws Exception {
		Optional<Product> Optionalproduct = productRepository.findById(product.getId());
		Optionalproduct.orElseThrow(() -> new Exception("Product not found"));
		return productRepository.save(product) != null;
	}

	@Transactional
	public boolean Addlikes(Product product) throws Exception {
		Optional<Product> Optionalproduct = productRepository.findById(product.getId());
		Optionalproduct.orElseThrow(() -> new Exception("Product not found"));
		Product p=new Product();
		p=Optionalproduct.get();
		p.setLikes(p.getLikes()+1);
		return productRepository.save(p) != null;
	}
	
	@Transactional
	public boolean Dislike(Product product) throws Exception {
		Optional<Product> Optionalproduct = productRepository.findById(product.getId());
		Optionalproduct.orElseThrow(() -> new Exception("Product not found"));
		Product p=new Product();
		p=Optionalproduct.get();
		p.setLikes(p.getLikes()-1);
		return productRepository.save(p) != null;
	}
	
	
	
	@Transactional
	public Long count() {

		return productRepository.count();
	}

	

}
