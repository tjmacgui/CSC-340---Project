package csc340project.example.springio.SteamAPI;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingRepo;
import csc340project.example.springio.User.OwnedGame.OwnedGame;
import csc340project.example.springio.User.OwnedGame.OwnedGameRepository;
import csc340project.example.springio.User.User;
import csc340project.example.springio.User.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Service
public class SteamAPI {
    private static final String API_KEY = "A0E2C7AD889381D6BAD14A50C4125BA6";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnedGameRepository ownedGameRepository;

    @Autowired
    private ListingRepo listingRepo;

    //get request stuff
    private JSONObject sendGetRequest(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    //gets steam id from username
    public String getSteamID(String username) throws Exception {
        String url = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + API_KEY + "&vanityurl=" + username;
        JSONObject response = sendGetRequest(url);
        return response.getJSONObject("response").getString("steamid");
    }

    //gets owned games by steam id
    public JSONObject getOwnedGames(String steamID) throws Exception {
        String url = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" + API_KEY + "&steamid=" + steamID + "&include_appinfo=true&format=json";
        return sendGetRequest(url).getJSONObject("response");
    }

    @Transactional
    public void linkSteamAccount(String username, String steamUsername) throws Exception {
        String steamID = getSteamID(steamUsername);
        JSONObject ownedGames = getOwnedGames(steamID);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        for (Object game : ownedGames.getJSONArray("games")) {
            //single json game going into individual variables
            JSONObject gameObj = (JSONObject) game;
            int appId = gameObj.getInt("appid");
            String name = gameObj.getString("name");
            String gameImgUrl = "https://steamcdn-a.akamaihd.net/steam/apps/" + appId + "/library_600x900.jpg";
            String genre = gameObj.optString("genre", "Unknown");
            Date releaseDate = new Date(gameObj.optLong("release_date", 0) * 1000);

            //check if the listing already exists
            Listing listing = listingRepo.findBySteamId(appId).orElse(new Listing(appId, genre, gameImgUrl, releaseDate, name));
            listingRepo.save(listing);

            //check if the ownedGame already exists
            if (!ownedGameRepository.findByUserAndListing(user, listing).isPresent()) {
                //save owned game
                OwnedGame ownedGame = new OwnedGame();
                ownedGame.setUser(user);
                ownedGame.setListing(listing);
                ownedGameRepository.save(ownedGame);
            }
        }
    }
}
