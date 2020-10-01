package dmg.base_authorization.controller;

import dmg.base_authorization.service.UserService;
import dmg.base_authorization.to.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static dmg.base_authorization.controller.Page.LOGIN;
import static dmg.base_authorization.controller.Page.REGISTRATION;

@Controller
@RequestMapping(value = "/" + REGISTRATION)
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model) {
        model.addAttribute("newUser", new NewUser());
        return REGISTRATION;
    }

    @PostMapping
    public String register(@ModelAttribute("newUser") @Valid NewUser newUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION;
        }
        try {
            userService.create(newUser);
            return LOGIN;
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return REGISTRATION;
        }
    }
}
