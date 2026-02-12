package edu.co.bookingSystem.service;

import edu.co.bookingSystem.model.User;
import edu.co.bookingSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId().isBlank()) {
            user.setId(UUID.randomUUID().toString());
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User not found with id: " + userId);
        }
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
