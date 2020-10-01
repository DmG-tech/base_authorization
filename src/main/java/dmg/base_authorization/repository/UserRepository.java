package dmg.base_authorization.repository;

import dmg.base_authorization.entities.User;

import java.util.Collection;

public interface UserRepository {

    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    // null if not found
    User getByName(String name);

    Collection<User> getAll();
}
