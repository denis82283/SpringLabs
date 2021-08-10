package en.app.springlabs.Repos;

import en.app.springlabs.Domain.UserDto;
import org.springframework.security.core.userdetails.User;

public interface IUserService {
    User registerNewUserAccount(UserDto userDTO) throws UserAlreadyExistException;
}
