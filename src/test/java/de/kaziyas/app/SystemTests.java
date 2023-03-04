package de.kaziyas.app;

import de.kaziyas.app.model.CustomJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.assertj.core.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 03/March/2023
 * @project comicStripsReader
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SystemTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetStrips() {
        String url = "http://localhost:" + port + "/";

        CustomJson[] strips = restTemplate.getForObject(url, CustomJson[].class);
        Assertions.assertThat(strips).hasSize(20);
        Assertions.assertThat(strips).extracting(CustomJson::getTitle).contains("Fanservice");
    }
}
