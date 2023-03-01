package de.kaziyas.app.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 01/March/2023
 * @project comic strips reader
 */

public final class CustomJson {

    private final String title;
    private final String browserViewURL;
    private final LocalDate publishingDate;
    private final String imageURL;

    public CustomJson(String title, String browserViewURL, LocalDate publishingDate,
                      String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
        this.publishingDate = getFormattedPublishingDate(publishingDate);
        this.browserViewURL = browserViewURL;
    }

    public final String getTitle() {
        return title;
    }

    public final String getImageURL() {
        return imageURL;
    }

    public final LocalDate getPublishingDate() {
        return publishingDate;
    }

    public final String getBrowserViewURL() {
        return browserViewURL;
    }

    private LocalDate getFormattedPublishingDate(LocalDate publishingDate) {
        publishingDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        return publishingDate;
    }
}
