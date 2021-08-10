package en.app.springlabs.Repos;

import en.app.springlabs.Domain.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDto, Long> {
    UserDto findByUsername(String username);
}
