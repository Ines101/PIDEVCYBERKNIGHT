package cyberknight.pidev.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cyberknight.pidev.model.verificationToken;

import java.util.Optional;

@Repository
public interface verificationTokenRepository extends JpaRepository<verificationToken, Long> {
    Optional<verificationToken> findBytoken(String token);
}