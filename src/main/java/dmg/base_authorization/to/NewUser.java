package dmg.base_authorization.to;

import dmg.base_authorization.util.annotation.FieldMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "confirmPassword", message = "{validation.confirm-password}")
public class NewUser {

    @NotBlank(message = "{validation.required-field}")
    @Size(min = 2, max = 100, message = "{validation.name-size}")
    private String name;

    @Email(message = "{validation.email-format}")
    @NotBlank(message = "{validation.required-field}")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "{validation.required-field}")
    @Size(min = 2, max = 100, message = "{validation.password-size}")
    private String password;

    private String confirmPassword;

    public NewUser() { }

    public NewUser(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
