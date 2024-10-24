package com.eletrinho.springmongo.services;

import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.repository.UserRepository;
import com.eletrinho.springmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void deleteById(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User put(User user) {
        User obj = findById(user.getId());
        obj.setEmail(user.getEmail());
        obj.setName(user.getName());
        return userRepository.save(obj);
    }
}
