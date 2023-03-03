package de.kaziyas.app;

import de.kaziyas.app.controller.ComicStripsController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 03/March/2023
 * @project comicStripsReader
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ComicStripsApplicationTests {

    @Autowired
    ComicStripsController comicStripsController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(comicStripsController).isNotNull();
    }
}
