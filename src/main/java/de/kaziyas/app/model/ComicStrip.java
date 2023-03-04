package de.kaziyas.app.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yaser.kazerooni (yaser.kazerooni@gmail.com)
 * @created 01/March/2023
 * @project comic strips reader
 */

public class ComicStrip {

    private String title;
    private String browserViewURL;
    private LocalDate publishingDate;
    private String imageURL;

    public ComicStrip() {
        super();
    }

    public ComicStrip(String title, String browserViewURL, LocalDate publishingDate,
                      String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
        this.publishingDate = getFormattedPublishingDate(publishingDate);
        this.browserViewURL = browserViewURL;
    }

    private LocalDate getFormattedPublishingDate(LocalDate publishingDate) {
        publishingDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return publishingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrowserViewURL() {
        return browserViewURL;
    }

    public void setBrowserViewURL(String browserViewURL) {
        this.browserViewURL = browserViewURL;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
