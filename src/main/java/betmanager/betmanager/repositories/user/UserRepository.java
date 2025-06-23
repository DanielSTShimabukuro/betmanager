package betmanager.betmanager.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import betmanager.betmanager.models.user.User;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserById(String id);
}
