package csc340project.example.springio.User;

import csc340project.example.springio.SteamAPI.SteamAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SteamAPI steamAPI;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("ROLE_USER")); // Default role
        }
        userRepository.save(user);
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User userId (Integer userId){return userRepository.findById(userId).orElse(null);}

    //admin searching
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //admin unflagging user
    public void unflagUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
            User user = optionalUser.get();
            user.setFlagged(false);
            userRepository.save(user);

    }

    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Async
    public void linkSteamAccount(String username, String steamUsername) throws Exception {
        steamAPI.linkSteamAccount(username, steamUsername);
    }

}
