package csc340project.example.springio.User.BannedAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannedAccountService {
    @Autowired
    private BannedAccountRepository bannedAccountRepository;

    public List<BannedAccount> getAllBannedAccounts() {
        return bannedAccountRepository.findAll();
    }

    public BannedAccount saveBannedAccount(BannedAccount bannedAccount) {
        return bannedAccountRepository.save(bannedAccount);
    }

    public Optional<BannedAccount> getBannedAccountById(Integer id) {
        return bannedAccountRepository.findById(id);
    }

    public void deleteBannedAccount(Integer id) {
        bannedAccountRepository.deleteById(id);
    }
}
