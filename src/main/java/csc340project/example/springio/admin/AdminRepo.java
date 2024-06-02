package csc340project.example.springio.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<SysAdmin, Integer> {
}
