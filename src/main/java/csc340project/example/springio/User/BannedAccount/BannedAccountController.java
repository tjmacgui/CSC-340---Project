package csc340project.example.springio.User.BannedAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/bannedAccounts")
public class BannedAccountController {

    @Autowired
    private BannedAccountService bannedAccountService;

    @GetMapping("/")
    public ResponseEntity<List<BannedAccount>> getAllBannedAccounts() {
        List<BannedAccount> bannedAccounts = bannedAccountService.getAllBannedAccounts();
        return ResponseEntity.ok(bannedAccounts);
    }

    @PostMapping("/")
    public ResponseEntity<BannedAccount> createBannedAccount(@RequestBody BannedAccount bannedAccount) {
        BannedAccount savedBannedAccount = bannedAccountService.saveBannedAccount(bannedAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBannedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BannedAccount> getBannedAccountById(@PathVariable Integer id) {
        Optional<BannedAccount> bannedAccount = bannedAccountService.getBannedAccountById(id);
        return bannedAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBannedAccount(@PathVariable Integer id) {
        bannedAccountService.deleteBannedAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}