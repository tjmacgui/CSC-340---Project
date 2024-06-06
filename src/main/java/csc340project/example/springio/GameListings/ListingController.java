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

//    @GetMapping("/error")
//    public String errorMessage(){
//        return "/error";
//    }
    @GetMapping("/")
    public String getAllListings(Model model) {
        model.addAttribute("listingList",listingService.getAllListings());
        return "/Admin Pages/listing-page";
    }

    @PostMapping("/create")
    public String createListing(@RequestBody Listing listing) {
        listingService.saveListing(listing);
        return "redirect:/games/";
    }

    @GetMapping("/add")
    public String createPage(){
        return "/Admin Pages/create-listing";
    }
    @GetMapping("/{id}")
    public String getListingById(@PathVariable int id, Model model) {
        model.addAttribute("listing", listingService.getGameListingById(id));
        return "/Admin Pages/listing-page";
    }

    @DeleteMapping("/{id}")
    public List<Listing> deleteListing(@PathVariable Integer id) {
        listingService.deleteListing(id);
        return listingService.getAllListings();
    }
}
