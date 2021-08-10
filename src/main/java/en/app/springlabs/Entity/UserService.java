package en.app.springlabs.Entity;

import en.app.springlabs.Domain.UserDto;
import en.app.springlabs.Repos.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        // the rest of the registration operation
    }
    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}