package csc340project.example.springio.User.Friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;

    public List<Friend> getAllFriends() {
        return friendRepository.findAll();
    }

    public Friend saveFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public Optional<Friend> getFriendById(Integer id) {
        return friendRepository.findById(id);
    }

    public void deleteFriend(Integer id) {
        friendRepository.deleteById(id);
    }

}
