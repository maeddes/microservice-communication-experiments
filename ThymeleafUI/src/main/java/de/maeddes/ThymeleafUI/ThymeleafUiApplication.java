package de.maeddes.ThymeleafUI;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String items(Model model) {

		System.out.println(" Invoking: " + endpoint + "/todos/");
		// Version A - direct
		ResponseEntity<Todo[]> response = null;
		
		// Version B - String and JSON Parsing
		//ResponseEntity<String> response = null;
		Todo[] todoItems = null;

		try {

			// Version A
			response = template.getForEntity(endpoint + "/todos", Todo[].class);
			System.out.println("Response: "+response.getBody());
			todoItems = response.getBody();
		
			// Version B
			// response = template.getForEntity(endpoint + "/todos", String.class);
			// ObjectMapper mapper = new ObjectMapper();
			// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
			// todoItems = mapper.readValue(response.getBody().toString(), Todo[].class);

			// Expriments for HAL Browsing
			//JsonNode jsNode = mapper.readTree(response.getBody());
			//String todosNode = jsNode.at("/_embedded/todos").toString();
			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Backend down");
			String[] items = new String[] { "Fix your backend" };
			model.addAttribute("items", items);
		}

		if (todoItems != null){
			
			List<String> items = new ArrayList<String>();
			for(int i = 0; i < todoItems.length; i++)
			  items.add(todoItems[i].getName());
			model.addAttribute("items", items);
			System.out.println("Items: "+items);

		} 

		System.out.println("Returning: "+model);
		return "items";

	}

	@PostMapping("/")
	public String addItem(@RequestParam String description) {

		System.out.println("New ToDo: "+description);
		
		if(description.equals("")){

			System.out.println("Empty String");
			return "redirect:/";

		} 

		HttpEntity<Todo> request = new HttpEntity<>(new Todo(description));

		try {

			template.postForEntity(endpoint + "/todos", request, Todo.class);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(" POST failed ");

		}
		return "redirect:/";

	}

	@PostMapping("/delete/{toDo}")
	public String setItemDone(@PathVariable String toDo) {

		System.out.println("Delete ToDo String: "+toDo);

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


