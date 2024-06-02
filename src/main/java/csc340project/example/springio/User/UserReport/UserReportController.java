package csc340project.example.springio.User.UserReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/userReports")
public class UserReportController {
    @Autowired
    private UserReportService userReportService;

    @GetMapping
    public String getAllUserReports() {
        List<UserReport> userReports = userReportService.getAllUserReports();
        StringBuilder response = new StringBuilder();
        for (UserReport userReport : userReports) {
            response.append(userReport.getId()).append(", ")
                    .append(userReport.getReportReason()).append(", ")
                    .append(userReport.getReporter().getUserId()).append(", ")
                    .append(userReport.getReported().getUserId()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createUserReport(@RequestBody UserReport userReport) {
        UserReport savedUserReport = userReportService.saveUserReport(userReport);
        return "UserReport created with ID: " + savedUserReport.getId();
    }

    @GetMapping("/{id}")
    public String getUserReportById(@PathVariable Integer id) {
        Optional<UserReport> userReport = userReportService.getUserReportById(id);
        return userReport.map(value -> value.getId() + ", " +
                value.getReportReason() + ", " +
                value.getReporter().getUserId() + ", " +
                value.getReported().getUserId()).orElse("UserReport not found");
    }

    @DeleteMapping("/{id}")
    public String deleteUserReport(@PathVariable Integer id) {
        userReportService.deleteUserReport(id);
        return "UserReport with ID " + id + " deleted";
    }

}
