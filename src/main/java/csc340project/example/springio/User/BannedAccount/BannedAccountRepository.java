package csc340project.example.springio.User.BannedAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedAccountRepository extends JpaRepository<BannedAccount, Integer> {
}
