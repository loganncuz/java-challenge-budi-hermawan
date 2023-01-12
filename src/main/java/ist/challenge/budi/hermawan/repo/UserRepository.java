package ist.challenge.budi.hermawan.repo;

import ist.challenge.budi.hermawan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    Optional<User> findById(Long id);
}
