package cyberknight.pidev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cyberknight.pidev.model.reclamation;
import cyberknight.pidev.model.user;
@Repository
public interface reclamationRepository extends JpaRepository<reclamation, Long> {
	Optional<reclamation> findByreclamtionId(Long reclamationId);
	List<reclamation> findAll();
	List<reclamation> findAllByuser(user userId);
	List<reclamation> findByuserAndSubjectContaining(user user,String subject);
}
