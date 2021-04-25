package cyberknight.pidev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cyberknight.pidev.model.role;

@Repository
public interface roleRepository extends JpaRepository<role,Long> {
	Optional<role> findByroleName(String roleName);
	Optional<role> findByroleId(long roleId);
	List<role> findAll();

}
