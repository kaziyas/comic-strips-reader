package de.kaziyas.app.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.kaziyas.app.model.CustomJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 01/March/2023
 * @project comic strips reader
 */

@Component
public class XKCDReader {

    private static final String XKCD_NUMBER_OF_FILE_URL = "https://xkcd.com/info.0.json";

    public List<CustomJson> getLast10Records() throws IOException {
        int xkcdNumberkcd = getLastXkcdNumber();
        final List<CustomJson> xkcdJsons = new ArrayList<>();
        IntStream.range(xkcdNumberkcd - 10, xkcdNumberkcd).forEach(i ->
        {
            CustomJson json;
            try {
                json = xkcdParser(i);
            } catch (IOException e) {
                return;
            }
            xkcdJsons.add(json);
        });

        return xkcdJsons;
    }

    private CustomJson xkcdParser(int id) throws IOException {
        URL url = new URL("https://xkcd.com/" + id + "/info.0.json");
        final JsonElement jsonElement = getJsonElement(url);

        return getCustomJson(jsonElement);
    }

    private int getLastXkcdNumber() throws IOException {
        URL url = new URL(XKCD_NUMBER_OF_FILE_URL);

        return getJsonElement(url).getAsJsonObject().get("num").getAsInt();
    }

    private JsonElement getJsonElement(URL url) throws IOException {
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonElement jsonElement = JsonParser.parseReader
                (new InputStreamReader((InputStream) request.getContent()));
        request.disconnect();

        return jsonElement;
    }

    private CustomJson getCustomJson(JsonElement jsonElement) {
        final LocalDate myDate = getLocalDate(jsonElement);
        final String title = jsonElement.getAsJsonObject().get("title").getAsString();
        final String link = jsonElement.getAsJsonObject().get("link").getAsString();
        final String img = jsonElement.getAsJsonObject().get("img").getAsString();

        return new CustomJson(title, link, myDate, img);
    }

    private LocalDate getLocalDate(JsonElement jsonElement) {
        final int year = jsonElement.getAsJsonObject().get("year").getAsInt();
        final int month = jsonElement.getAsJsonObject().get("month").getAsInt();
        final int day = jsonElement.getAsJsonObject().get("day").getAsInt();

        return LocalDate.of(year, month, day);
    }
}
