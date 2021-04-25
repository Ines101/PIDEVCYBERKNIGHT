package cyberknight.pidev.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cyberknight.pidev.model.Ads;
import cyberknight.pidev.model.Product;



public interface AdsRepository<A> extends JpaRepository<Ads, Long>{
	




}
