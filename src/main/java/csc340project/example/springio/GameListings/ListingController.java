package csc340project.example.springio.GameListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/games")
public class ListingController {
    @Autowired
    private ListingService listingService;

}
