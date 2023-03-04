package de.kaziyas.app.controller;

import com.rometools.rome.io.FeedException;
import de.kaziyas.app.model.ComicStrip;
import de.kaziyas.app.service.ComicStripsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 01/March/2023
 * @project comic strips reader
 */

@RestController
public class ComicStripsController {

    private final ComicStripsService comicStripsService;

    public ComicStripsController(ComicStripsService comicStripsService) {
        this.comicStripsService = comicStripsService;
    }

    @GetMapping("/")
    public List<ComicStrip> getComicStrips() throws IOException, FeedException {

        return comicStripsService.getComicStrips();
    }
}