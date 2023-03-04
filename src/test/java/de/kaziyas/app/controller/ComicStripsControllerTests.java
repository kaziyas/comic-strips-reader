package de.kaziyas.app.controller;

import de.kaziyas.app.model.ComicStrip;
import de.kaziyas.app.service.ComicStripsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    final List<ComicStrip> theComicStrips = new ArrayList<>();

    @BeforeEach
    void Setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetComicStrips() throws Exception {
        ComicStrip comicStrip = new ComicStrip("Strip" + 1, "www.test.de/" + 1 + ".html",
                LocalDate.now().plusDays(1),
                "www.test.de/test" + 1 + ".png");
        theComicStrips.add(comicStrip);

        Mockito.when(comicStripsService.getComicStrips()).thenReturn(theComicStrips);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Strip1")));
    }
}
