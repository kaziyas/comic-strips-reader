package de.kaziyas.app.util;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import de.kaziyas.app.model.ComicStrip;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 01/March/2023
 * @project comic strips reader
 */

@Component
public class RSSFeedsParser {

    private static final String RSS_FEED_URL = "http://feeds.feedburner.com/PoorlyDrawnLines";

    public List<ComicStrip> getLast10Feeds() throws IOException, FeedException {
        URL url = new URL(RSS_FEED_URL);
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(url));

        return feed.getEntries().stream().map(this::syndEntry2CustomJson)
                .collect(Collectors.toList());
    }

    private ComicStrip syndEntry2CustomJson(SyndEntry entry) {
        String imageURL = entry.getContents().get(0).getValue();
        imageURL = HtmlParser.parse(imageURL);

        String title = entry.getTitleEx().getValue();
        String link = entry.getLink();
        Date publishedDate = entry.getPublishedDate();
        LocalDate date = publishedDate.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();

        return new ComicStrip(
                title,
                link,
                date,
                (imageURL));
    }

    private static class HtmlParser {

        public static String parse(String html) {
            Document doc = Jsoup.parse(html);
            Element div = doc.select("div").first();
            Elements childes = div.getElementsByTag("img");

            return childes.attr("src");
        }
    }
}
