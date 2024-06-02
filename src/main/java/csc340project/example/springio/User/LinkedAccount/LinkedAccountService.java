package csc340project.example.springio.User.LinkedAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkedAccountService {
    @Autowired
    private LinkedAccountRepository linkedAccountRepository;

    public List<LinkedAccount> getAllLinkedAccounts() {
        return linkedAccountRepository.findAll();
    }

    public LinkedAccount saveLinkedAccount(LinkedAccount linkedAccount) {
        return linkedAccountRepository.save(linkedAccount);
    }

    public Optional<LinkedAccount> getLinkedAccountById(Integer id) {
        return linkedAccountRepository.findById(id);
    }

    public void deleteLinkedAccount(Integer id) {
        linkedAccountRepository.deleteById(id);
    }

}
