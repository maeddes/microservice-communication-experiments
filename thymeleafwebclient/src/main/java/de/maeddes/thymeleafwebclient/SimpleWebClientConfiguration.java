package de.maeddes.thymeleafwebclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SimpleWebClientConfiguration {
 
  private static final String BASE_URL = "http://localhost:8080";
  private static final Logger logger = LoggerFactory.getLogger(SimpleWebClientConfiguration.class);
 
  @Bean
  public WebClient webClientFromScratch() {

    logger.debug("Invoking WebClient");
 
    return WebClient.builder()
      .baseUrl(BASE_URL)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }
}