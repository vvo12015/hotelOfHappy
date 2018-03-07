package net.vrakin.services;

import net.vrakin.model.User;
import net.vrakin.repository.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ParentService<User, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long getId(User entity) {
        return entity.getUserId();
    }

    @Override
    public CrudRepository<User, Long> getRepository() {
        return this.userRepository;
    }

}
