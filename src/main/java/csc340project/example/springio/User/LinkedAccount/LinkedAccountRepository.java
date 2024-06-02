package csc340project.example.springio.User.LinkedAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkedAccountRepository extends JpaRepository<LinkedAccount, Integer> {
}
