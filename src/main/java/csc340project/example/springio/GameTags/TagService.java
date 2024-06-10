package csc340project.example.springio.GameTags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTagsForGame(int gameId) {
        return tagRepository.getAllTagsForGame(gameId);
    }
}
