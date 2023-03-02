package de.kaziyas.app.controller;

import com.rometools.rome.io.FeedException;
import de.kaziyas.app.model.CustomJson;
import de.kaziyas.app.service.ComicStripsService;
import de.kaziyas.app.util.RSSFeedsParser;
import de.kaziyas.app.util.XKCDReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CustomJson> getComicStrips() throws IOException, FeedException {

        return comicStripsService.getComicStrips();
    }
}