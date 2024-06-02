package csc340project.example.springio.User.BannedAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/bannedAccounts")
public class BannedAccountController {
    @Autowired
    private BannedAccountService bannedAccountService;

    @GetMapping
    public String getAllBannedAccounts() {
        List<BannedAccount> bannedAccounts = bannedAccountService.getAllBannedAccounts();
        StringBuilder response = new StringBuilder();
        for (BannedAccount bannedAccount : bannedAccounts) {
            response.append(bannedAccount.getId()).append(", ")
                    .append(bannedAccount.getReason()).append(", ")
                    .append(bannedAccount.getUser().getUserId()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createBannedAccount(@RequestBody BannedAccount bannedAccount) {
        BannedAccount savedBannedAccount = bannedAccountService.saveBannedAccount(bannedAccount);
        return "BannedAccount created with ID: " + savedBannedAccount.getId();
    }

    @GetMapping("/{id}")
    public String getBannedAccountById(@PathVariable Integer id) {
        Optional<BannedAccount> bannedAccount = bannedAccountService.getBannedAccountById(id);
        return bannedAccount.map(value -> value.getId() + ", " +
                value.getReason() + ", " +
                value.getUser().getUserId()).orElse("BannedAccount not found");
    }

    @DeleteMapping("/{id}")
    public String deleteBannedAccount(@PathVariable Integer id) {
        bannedAccountService.deleteBannedAccount(id);
        return "BannedAccount with ID " + id + " deleted";
    }
    /** would delte the user
    @DeleteMapping("/{id}")
    public String deleteBannedAccount(@PathVariable Integer id) {
        Optional<BannedAccount> bannedAccount = bannedAccountService.getBannedAccountById(id);
        if (bannedAccount.isPresent()) {
            userService.deleteUser(bannedAccount.get().getUser().getUserId());
            return "User and BannedAccount with ID " + id + " deleted";
        } else {
            return "BannedAccount not found with ID " + id;
        }
    }
    **/

}
