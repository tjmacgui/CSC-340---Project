package csc340project.example.springio.User.Friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping
    public String getAllFriends() {
        List<Friend> friends = friendService.getAllFriends();
        StringBuilder response = new StringBuilder();
        for (Friend friend : friends) {
            response.append(friend.getId()).append(", ")
                    .append(friend.getFriendName()).append(", ")
                    .append(friend.getUser().getUserId()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createFriend(@RequestBody Friend friend) {
        Friend savedFriend = friendService.saveFriend(friend);
        return "Friend created with ID: " + savedFriend.getId();
    }

    @GetMapping("/{id}")
    public String getFriendById(@PathVariable Integer id) {
        Optional<Friend> friend = friendService.getFriendById(id);
        return friend.map(value -> value.getId() + ", " +
                value.getFriendName() + ", " +
                value.getUser().getUserId()).orElse("Friend not found");
    }

    @DeleteMapping("/{id}")
    public String deleteFriend(@PathVariable Integer id) {
        friendService.deleteFriend(id);
        return "Friend with ID " + id + " deleted";
    }

}
