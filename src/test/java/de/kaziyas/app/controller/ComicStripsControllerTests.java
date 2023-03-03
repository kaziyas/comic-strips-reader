package de.kaziyas.app.controller;

import com.rometools.rome.io.FeedException;
import de.kaziyas.app.model.CustomJson;
import de.kaziyas.app.service.ComicStripsService;
import de.kaziyas.app.util.RSSFeedsParser;
import de.kaziyas.app.util.XKCDReader;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 01/March/2023
 * @project comic strips reader
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(ComicStripsController.class)
public class ComicStripsControllerTests {

    @MockBean
    ComicStripsService comicStripsService;

    @Autowired
    MockMvc mockMvc;

    final List<CustomJson> theComicStrips = new ArrayList<>();

    @BeforeEach
    void Setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetComicStrips() throws Exception {
        CustomJson customJson = new CustomJson("Strip" + 1, "www.test.de/" + 1 + ".html",
                LocalDate.now().plusDays(1),
                "www.test.de/test" + 1 + ".png");
        theComicStrips.add(customJson);

        Mockito.when(comicStripsService.getComicStrips()).thenReturn(theComicStrips);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Strip1")));
    }
}
