package csc340project.example.springio.User.LinkedAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/linkedAccounts")
public class LinkedAccountController {
    @Autowired
    private LinkedAccountService linkedAccountService;

    @GetMapping
    public String getAllLinkedAccounts() {
        List<LinkedAccount> linkedAccounts = linkedAccountService.getAllLinkedAccounts();
        StringBuilder response = new StringBuilder();
        for (LinkedAccount linkedAccount : linkedAccounts) {
            response.append(linkedAccount.getId()).append(", ")
                    .append(linkedAccount.getAccountName()).append(", ")
                    .append(linkedAccount.getAccountType()).append(", ")
                    .append(linkedAccount.getUser().getUserId()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createLinkedAccount(@RequestBody LinkedAccount linkedAccount) {
        LinkedAccount savedLinkedAccount = linkedAccountService.saveLinkedAccount(linkedAccount);
        return "LinkedAccount created with ID: " + savedLinkedAccount.getId();
    }

    @GetMapping("/{id}")
    public String getLinkedAccountById(@PathVariable Integer id) {
        Optional<LinkedAccount> linkedAccount = linkedAccountService.getLinkedAccountById(id);
        return linkedAccount.map(value -> value.getId() + ", " +
                value.getAccountName() + ", " +
                value.getAccountType() + ", " +
                value.getUser().getUserId()).orElse("LinkedAccount not found");
    }

    @DeleteMapping("/{id}")
    public String deleteLinkedAccount(@PathVariable Integer id) {
        linkedAccountService.deleteLinkedAccount(id);
        return "LinkedAccount with ID " + id + " deleted";
    }


}
