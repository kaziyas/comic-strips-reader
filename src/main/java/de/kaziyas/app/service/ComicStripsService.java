package de.kaziyas.app.service;

import com.rometools.rome.io.FeedException;
import de.kaziyas.app.model.CustomJson;
import de.kaziyas.app.util.RSSFeedsParser;
import de.kaziyas.app.util.XKCDReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 02/March/2023
 * @project comicStripsReader
 */
@Service
public class ComicStripsService {
    private final XKCDReader xkcdReader;
    private final RSSFeedsParser rssFeedsParser;

    private final Comparator<CustomJson> compareByPublishingDate = Comparator.comparing(CustomJson::getPublishingDate, Comparator.reverseOrder());

    public ComicStripsService(XKCDReader xkcdReader, RSSFeedsParser rssFeedsParser) {
        this.xkcdReader = xkcdReader;
        this.rssFeedsParser = rssFeedsParser;
    }

    public List<CustomJson> getComicStrips() throws IOException, FeedException {
        final List<CustomJson> last20Records = new ArrayList<>();
        last20Records.addAll(rssFeedsParser.getLast10Feeds());
        last20Records.addAll(xkcdReader.getLast10Records());

        return last20Records
                .stream()
                .sorted(compareByPublishingDate)
                .collect(Collectors.toList());
    }
}
