package csc340project.example.springio.User.OwnedGame;


import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingRepo;
import csc340project.example.springio.GameListings.ListingService;

import csc340project.example.springio.User.User;
import csc340project.example.springio.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnedGameService {
    @Autowired
    private OwnedGameRepository ownedGameRepository;

    @Autowired
    private ListingRepo listingRepo;


}