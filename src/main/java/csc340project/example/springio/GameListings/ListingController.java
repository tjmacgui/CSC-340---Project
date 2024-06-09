package csc340project.example.springio.GameListings;

//import csc340project.example.Listing.Listing;
//import csc340project.example.Listing.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/games")
public class ListingController {
    @Autowired
    private ListingService listingService;

}
