# comic-strips-reader

A RESTful service that pulls the last 10 strips from two different sources, a public API for the Webcomic XKCD and an RSS feed, and combines them into a single JSON file (20 recent entries in total).

## Requirements
A public API for the webcomic XKCD – [https://xkcd.com/json.html](https://xkcd.com/json.html)

An RSS feed for PDL: [http://feeds.feedburner.com/PoorlyDrawnLines](http://feeds.feedburner.com/PoorlyDrawnLines)

## Getting Started
### Prerequisites

* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Maven](https://maven.apache.org/)

### Running the service

Run ```mvn spring-boot:run``` from the command line in the project's root folder. This will start the comic strips reader service under this URL: http://localhost:8080/

## Running the build
Run ```mvn clean install``` from the command line.

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/)
* [Gson](https://sites.google.com/site/gson/gson-user-guide)
* [ROME](https://rometools.github.io/rome/index.html)
* [JUnit](https://junit.org/junit5/)
* [Mockito](https://site.mockito.org/)
* [Maven](https://maven.apache.org/)

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Authors

* **[Yaser Kazerooni](https://www.linkedin.com/in/yaserkazerooni/)**
