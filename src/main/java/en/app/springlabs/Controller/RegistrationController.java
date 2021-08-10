package en.app.springlabs.Controller;

import en.app.springlabs.Domain.UserDto;
import en.app.springlabs.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDTO = new UserDto();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto,
            HttpServletRequest request,
            Errors errors) {

        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", userDto);
    }
}
