package dmg.base_authorization.util;

import dmg.base_authorization.repository.UserRepository;
import dmg.base_authorization.to.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

/*@Component
public class NewUserValidator implements Validator {

    private final UserRepository userRepository;

    private final javax.validation.Validator validator;

    @Autowired
    public NewUserValidator(UserRepository userRepository, javax.validation.Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewUser user = (NewUser) o;

        if (user.getEmail() != null)
        checkUniqueEmail(user, errors);
        if (user.getName() != null)
        checkUniqueName(user, errors);
        if (user.getPassword() != null && user.getConfirmPassword() != null)
        checkPasswordConfirm(user, errors);
    }

    private void checkUniqueEmail(NewUser user, Errors errors) {
        if (userRepository.getByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "User with this email already exists.");
        }
    }

    private void checkUniqueName(NewUser user, Errors errors) {
        if (userRepository.getByName(user.getName()) != null) {
            errors.rejectValue("name", "User with this name already exists.");
        }
    }

    private void checkPasswordConfirm(NewUser user, Errors errors) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Password mismatch.");
        }
    }
}*/
