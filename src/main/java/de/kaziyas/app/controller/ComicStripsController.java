package de.kaziyas.app.controller;

import com.rometools.rome.io.FeedException;
import de.kaziyas.app.model.CustomJson;
import de.kaziyas.app.util.RSSFeedsParser;
import de.kaziyas.app.util.XKCDReader;
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

    private final RSSFeedsParser rssFeedsParser;
    private final XKCDReader xkcdReader;

    public ComicStripsController(RSSFeedsParser rssFeedsParser,
                                 XKCDReader xkcdReader) {
        this.rssFeedsParser = rssFeedsParser;
        this.xkcdReader = xkcdReader;
    }

    @GetMapping("/")
    public List<CustomJson> getComicStrips() throws IOException, FeedException {

        final List<CustomJson> last20Records = new ArrayList<>();
        last20Records.addAll(rssFeedsParser.getLast10Feeds());
        last20Records.addAll(xkcdReader.getLast10Records());

        Comparator<CustomJson> compareByPublishingDate =
                Comparator.comparing(CustomJson::getPublishingDate, Comparator.reverseOrder());

        return last20Records
                .stream()
                .sorted(compareByPublishingDate)
                .collect(Collectors.toList());

    }
}