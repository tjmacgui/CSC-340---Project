package csc340project.example.springio.User.UserReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReportService {
    @Autowired
    private UserReportRepository userReportRepository;

    public List<UserReport> getAllUserReports() {
        return userReportRepository.findAll();
    }

    public UserReport saveUserReport(UserReport userReport) {
        return userReportRepository.save(userReport);
    }

    public Optional<UserReport> getUserReportById(Integer id) {
        return userReportRepository.findById(id);
    }

    public void deleteUserReport(Integer id) {
        userReportRepository.deleteById(id);
    }

}
