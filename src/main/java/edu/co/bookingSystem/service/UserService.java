package edu.co.bookingSystem.service;


import edu.co.bookingSystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(String id);

    List<User> all();

    void deleteById(String id);

    User update(User user, String userId);

    Optional<User> findByEmail(String email);
}
