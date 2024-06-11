package csc340project.example.springio.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
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

}
