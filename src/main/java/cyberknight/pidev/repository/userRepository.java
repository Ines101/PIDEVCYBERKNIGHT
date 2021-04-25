package cyberknight.pidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cyberknight.pidev.model.role;
import cyberknight.pidev.model.user;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<user, Long> {
	Optional<user> findByUsername(String username);
	List<user> findAll();
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
