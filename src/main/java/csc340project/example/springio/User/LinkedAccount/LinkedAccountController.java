package csc340project.example.springio.User.LinkedAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/linkedAccounts")
public class LinkedAccountController {

    @Autowired
    private LinkedAccountService linkedAccountService;

    @GetMapping("/")
    public ResponseEntity<List<LinkedAccount>> getAllLinkedAccounts() {
        List<LinkedAccount> linkedAccounts = linkedAccountService.getAllLinkedAccounts();
        return ResponseEntity.ok(linkedAccounts);
    }

    @PostMapping("/")
    public ResponseEntity<LinkedAccount> createLinkedAccount(@RequestBody LinkedAccount linkedAccount) {
        LinkedAccount savedLinkedAccount = linkedAccountService.saveLinkedAccount(linkedAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLinkedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkedAccount> getLinkedAccountById(@PathVariable Integer id) {
        Optional<LinkedAccount> linkedAccount = linkedAccountService.getLinkedAccountById(id);
        return linkedAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLinkedAccount(@PathVariable Integer id) {
        linkedAccountService.deleteLinkedAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
