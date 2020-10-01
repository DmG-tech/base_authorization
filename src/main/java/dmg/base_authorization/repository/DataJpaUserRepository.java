package dmg.base_authorization.repository;

import dmg.base_authorization.entities.Role;
import dmg.base_authorization.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DataJpaUserRepository implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final JpaUserRepository jpaUserRepository;

    public DataJpaUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return jpaUserRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return jpaUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return jpaUserRepository.getByEmail(email);
    }

    @Override
    public User getByName(String name) {
        return jpaUserRepository.getByName(name);
    }

    @Override
    public Collection<User> getAll() {
        return jpaUserRepository.findAll(SORT_NAME_EMAIL);
    }
}
