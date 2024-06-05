package csc340project.example.springio.User.UserReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/userReports")
public class UserReportController {

    @Autowired
    private UserReportService userReportService;

    @GetMapping("/")
    public ResponseEntity<List<UserReport>> getAllUserReports() {
        List<UserReport> userReports = userReportService.getAllUserReports();
        return ResponseEntity.ok(userReports);
    }

    @PostMapping("/")
    public ResponseEntity<UserReport> createUserReport(@RequestBody UserReport userReport) {
        UserReport savedUserReport = userReportService.saveUserReport(userReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserReport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReport> getUserReportById(@PathVariable Integer id) {
        Optional<UserReport> userReport = userReportService.getUserReportById(id);
        return userReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserReport(@PathVariable Integer id) {
        userReportService.deleteUserReport(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
