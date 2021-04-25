package cyberknight.pidev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cyberknight.pidev.model.Categoryproduct;
import cyberknight.pidev.repository.CategoryproductRepository;



@Service
public class CategoryproductService {

	@Autowired
	CategoryproductRepository<Categoryproduct> CategoryRepository;


	@Transactional
	public List<Categoryproduct> getAllCategory() {
		return (List<Categoryproduct>) CategoryRepository.findAll(Sort.by("id").ascending());
	}

	@Transactional
	public boolean addCategory(Categoryproduct Categoryproduct) {
		return CategoryRepository.save(Categoryproduct) != null;
	}

	@Transactional
	public boolean updateCategory(Categoryproduct Categoryproduct) {
		return CategoryRepository.save(Categoryproduct) != null;
	}

	@Transactional
	public boolean deleteCategory(Long CategoryId) {
		CategoryRepository.deleteById(CategoryId);
		return !CategoryRepository.existsById(CategoryId);

	}

}
