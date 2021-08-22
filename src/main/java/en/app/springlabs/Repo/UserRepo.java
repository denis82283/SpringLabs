package en.app.springlabs.Repo;

import en.app.springlabs.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(@Param("username") String username);
}
