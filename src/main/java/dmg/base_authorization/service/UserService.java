package dmg.base_authorization.service;

import dmg.base_authorization.entities.User;
import dmg.base_authorization.repository.UserRepository;
import dmg.base_authorization.to.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Locale;

import static dmg.base_authorization.util.UserUtil.createFromNew;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MessageSource messageSource;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }

    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {
        User user = getUserByNameOrEmail(nameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException(messageSource.getMessage("error.user-not-found", new Object[] {nameOrEmail}, Locale.getDefault()));
        }
        return user;
    }

    public User create(NewUser newUser) {
        Assert.notNull(newUser, messageSource.getMessage("error.null-object", new Object[] {"User"}, Locale.US));
        User user = createFromNew(newUser);
        if (isExistUser(user)) {
            throw new IllegalArgumentException(messageSource.getMessage("{error.user-exist}", null, Locale.getDefault()));
        }
        encodePassword(user);
        return userRepository.save(user);
    }

    private void encodePassword(User user) {
        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
    }

    private User getUserByNameOrEmail(String nameOrEmail) {
        User user = userRepository.getByName(nameOrEmail);
        if (user == null) {
            user = userRepository.getByEmail(nameOrEmail);
        }
        return user;
    }

    private boolean isExistUser(User user) {
        return userRepository.getByName(user.getName()) != null || userRepository.getByEmail(user.getEmail()) != null;
    }

    /*    public void delete(int id) {
        boolean isDeleted = userRepository.delete(id);
        Assert.isTrue(isDeleted, "user with" + id + "not found");
    }

    public User get(int id) {
        User user = userRepository.get(id);
        Assert.notNull(user, "user with" + id + "not found");
        return user;
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");

        User user = userRepository.getByEmail(email);
        Assert.notNull(user, "user with" + email + "not found");
        return user;
    }

    public List<User> getAll() {
        return (List<User>) userRepository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }*/
}
