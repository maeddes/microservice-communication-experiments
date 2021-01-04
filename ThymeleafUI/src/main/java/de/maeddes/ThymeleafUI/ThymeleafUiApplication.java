package de.maeddes.ThymeleafUI;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Controller
public class ThymeleafUiApplication {

	@Value("${backend.host:localhost}")
	String backendHost;

	@Value("${backend.port:8080}")
	String backendPort;

	String endpoint;

	RestTemplate template = new RestTemplate();

	@PostConstruct
	private void initEndpoint() {

		endpoint = "http://" + backendHost + ":" + backendPort;

	}

	@GetMapping
	public String getItems(Model model) {

		System.out.println(" Invoking: " + endpoint + "/todos/");
		ResponseEntity<String> response = null;

		try {

			//response = template.getForEntity(endpoint + "/todos", Todo[].class);
			response = template.getForEntity(endpoint + "/todos", String.class);
			System.out.println("Response: "+response.getBody());

			// ObjectMapper mapper = new ObjectMapper();
			// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// JsonNode jsNode = mapper.readTree(response.getBody());
			// String todosNode = jsNode.at("/_embedded/todos").toString();
			// System.out.println("TodosNode: "+todosNode);
			// List<Todo> todoItems = mapper.readValue(todosNode, new TypeReference<List<Todo>>() {
			// });



			System.out.println(todoItems);


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(" Backend down");
			String[] responseValue = new String[] { "Fix your backend" };
			model.addAttribute("items", responseValue);
		}

		// if (response != null){
		// 	Todo[] todos = response.getBody();
		// 	List<String> todoitems = new ArrayList<String>();
		// 	for(int i = 0; i < todos.length; i++)
		// 		todoitems.add(todos[i].getName());
		// 	model.addAttribute("items", todoitems);
		// } else{
		// 	String[] responseValue = new String[] { "Fix it" };
		// 	model.addAttribute("items", responseValue);
		// }

		return "items";

	}

	@PostMapping
	public String addItem(String toDo) {

		HttpEntity<Todo> request = new HttpEntity<>(new Todo(toDo));

		try {

			template.postForEntity(endpoint + "/todos", request, Todo.class);

		} catch (Exception e) {

			System.out.println(" POST failed ");

		}
		return "redirect:/";

	}

	@PostMapping("{toDo}")
	public String setItemDone(@PathVariable String toDo) {

		try {

			template.delete(endpoint + "/todos/" + toDo);

		} catch (Exception e) {

			System.out.println(" DELETE failed ");

		}
		return "redirect:/";

	}

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafUiApplication.class, args);
	}

}


