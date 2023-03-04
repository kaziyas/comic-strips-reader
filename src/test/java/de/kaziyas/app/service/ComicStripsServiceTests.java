package de.kaziyas.app.service;

import com.rometools.rome.io.FeedException;
import de.kaziyas.app.model.ComicStrip;
import de.kaziyas.app.util.RSSFeedsParser;
import de.kaziyas.app.util.XKCDReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 03/March/2023
 * @project comicStripsReader
 */

@ExtendWith(MockitoExtension.class)
public class ComicStripsServiceTests {

    @InjectMocks
    ComicStripsService service;

    @Mock
    RSSFeedsParser rssFeedsParser;

    @Mock
    XKCDReader xkcdReader;

    @Test
    public void testGetComicStrips() throws IOException, FeedException {
        when(rssFeedsParser.getLast10Feeds()).thenReturn(getRssFeeds());
        when(xkcdReader.getLast10Records()).thenReturn(getXKCDs());

        List<ComicStrip> comicStrips = service.getComicStrips();

        assertEquals(20, comicStrips.size());
        assertEquals("comic5", comicStrips.get(0).getTitle());

        verify(rssFeedsParser, times(1)).getLast10Feeds();
        verify(xkcdReader, times(1)).getLast10Records();

        List<ComicStrip> the20ComicStrips = new ArrayList<>(20);
        the20ComicStrips.addAll(getRssFeeds());
        the20ComicStrips.addAll(getXKCDs());

        assertNotEquals(comicStrips.get(5).getPublishingDate(), the20ComicStrips.get(5).getPublishingDate());
        assertEquals(comicStrips.get(5).getPublishingDate(), getSortedList(the20ComicStrips).get(5).getPublishingDate());
    }

    private List<ComicStrip> getRssFeeds() {
        List<ComicStrip> returnValue = new ArrayList<>();
        IntStream.rangeClosed(1, 5).forEach(i -> {
            ComicStrip comicStrip = new ComicStrip("Strip" + i, "www.test.de/" + i + ".html",
                    LocalDate.now().plusDays(i),
                    "www.test.de/test" + i + ".png");
            returnValue.add(comicStrip);
        });

        IntStream.rangeClosed(6, 10).forEach(i -> {
            ComicStrip comicStrip = new ComicStrip("Strip" + i, "www.test.de/" + i + ".html",
                    LocalDate.now().minusDays(i),
                    "www.test.de/test" + i + ".png");
            returnValue.add(comicStrip);
        });

        return returnValue;
    }

    private List<ComicStrip> getXKCDs() {
        List<ComicStrip> returnValue = new ArrayList<>();
        IntStream.rangeClosed(1, 5).forEach(i -> {
            ComicStrip comicStrip = new ComicStrip("comic" + i, "www.comic.de/" + i + ".html",
                    LocalDate.now().plusDays(i * 2),
                    "www.comic.de/comic" + i + ".jpg");
            returnValue.add(comicStrip);
        });

        IntStream.rangeClosed(6, 10).forEach(i -> {
            ComicStrip comicStrip = new ComicStrip("Strip" + i, "www.comic.de/" + i + ".html",
                    LocalDate.now().minusDays(i * 2),
                    "www.comic.de/comic" + i + ".jpg");
            returnValue.add(comicStrip);
        });

        return returnValue;
    }

    private List<ComicStrip> getSortedList(List<ComicStrip> comicStrips) {
        Comparator<ComicStrip> compareByPublishingDate =
                Comparator.comparing(ComicStrip::getPublishingDate, Comparator.reverseOrder());

        return comicStrips
                .stream()
                .sorted(compareByPublishingDate)
                .collect(Collectors.toList());
    }
}
