package cyberknight.pidev.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cyberknight.pidev.model.Categoryproduct;



public interface CategoryproductRepository<C> extends JpaRepository<Categoryproduct, Long> {


}
