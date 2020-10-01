package dmg.base_authorization.util;

import dmg.base_authorization.entities.Role;
import dmg.base_authorization.entities.User;
import dmg.base_authorization.to.NewUser;

public class UserUtil {

    public static User createFromNew(NewUser newUser) {
        return new User(newUser.getName(), newUser.getEmail(), newUser.getPassword(), Role.USER);
    }

    /*public static User createFromTo(NewUser newUser) {
        return new User(null, newUser.getName(), newUser.getEmail(), newUser.getPassword(), Role.USER);
    }

    public static NewUser getTo(User user) {
        return new NewUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
    }

    public static User updateFromTo(User user, NewUser newUser) {
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail().toLowerCase());
        user.setPassword(newUser.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }*/
}
