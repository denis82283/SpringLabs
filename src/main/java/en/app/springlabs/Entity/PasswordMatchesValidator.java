package en.app.springlabs.Entity;

import en.app.springlabs.Domain.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto userDTO = (UserDto) obj;
        return userDTO.getPassword().equals(userDTO.getMatchingPassword());
    }
}