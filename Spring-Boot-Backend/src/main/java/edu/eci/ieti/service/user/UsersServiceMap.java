package edu.eci.ieti.service.user;

import edu.eci.ieti.repository.user.User;
import edu.eci.ieti.service.user.persistence.UserServicePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService {

    @Autowired
    private UserServicePersistence userServicePersistence;

    @Override
    public User save(User user) {
        userServicePersistence.save(user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.of(userServicePersistence.findById(id));
    }

    @Override
    public List<User> all() {
        return userServicePersistence.all();
    }

    @Override
    public void deleteById(String id) {
        userServicePersistence.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        return userServicePersistence.update(user,userId);
    }
}
