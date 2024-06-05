package csc340project.example.springio.User.Friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/")
    public ResponseEntity<List<Friend>> getAllFriends() {
        List<Friend> friends = friendService.getAllFriends();
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/")
    public ResponseEntity<Friend> createFriend(@RequestBody Friend friend) {
        Friend savedFriend = friendService.saveFriend(friend);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFriend);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getFriendById(@PathVariable Integer id) {
        Optional<Friend> friend = friendService.getFriendById(id);
        return friend.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Integer id) {
        friendService.deleteFriend(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}