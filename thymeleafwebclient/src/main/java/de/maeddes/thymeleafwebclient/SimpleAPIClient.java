package de.maeddes.thymeleafwebclient;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class SimpleAPIClient { 

  //private static final Logger logger = LoggerFactory.getLogger(SimpleAPIClient.class);
  private final WebClient defaultWebClient;
  
  // inject the configured WebClient @Bean from SimpleWebClientConfigration
  public SimpleAPIClient(WebClient defaultWebClient) {
    this.defaultWebClient = defaultWebClient;
  }
 
  public JsonNode getTodoJSONFromAPI() {
    return this.defaultWebClient
        .get()
        .uri("/todos/one")
        .retrieve()
        .bodyToMono(JsonNode.class)
        .block();
  }

  /**
   * GET Single item
   * 
   * @param todo
   * @return
   */

  public Todo getTodoFromAPI(String todo) {
    return this.defaultWebClient
        .get()
        .uri("/todos/"+todo)
        .retrieve()
        .bodyToMono(Todo.class)
        .block();

  }

  /**
   * GET List of Todos
   * @return
   */

  public Todo[] getTodosFromAPI() {
    return this.defaultWebClient
        .get()
        .uri("/todos/")
        .retrieve()
        .bodyToMono(Todo[].class)
        .block();

  }

  /**
   * POST with String Param
   * 
   * @param todo
   * @return
   */

  public Todo postToTodoAPI(String todo) {
    return this.defaultWebClient
      .post()
      .uri("/todos/"+todo)
      .retrieve()
      .bodyToMono(Todo.class)
      .block();
  }

  /**
   * POST with Object Param
   * 
   * @param todo
   * @return
   */

  public Todo postToTodoAPI(Todo todo) {
    return this.defaultWebClient
      .post()
      .uri("/todos/")
      .body(Mono.just(todo),Todo.class)
      .retrieve()
      .bodyToMono(Todo.class)
      .block();
  }

  /**
   * DELETE with String Param
   * 
   * @param todo
   * @return
   */

  public Void deleteTodoFromAPI(String todo){
      return this.defaultWebClient
        .delete()
        .uri("/todos/"+todo)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

}