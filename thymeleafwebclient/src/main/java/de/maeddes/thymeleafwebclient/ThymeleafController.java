package de.maeddes.thymeleafwebclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

    @Value("${message}")
    private String message;

    private List<String> stringList = Arrays.asList("one","two","three");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("list", stringList);

        return "hello"; 
    }

    // /hello?name=Matthias
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") 
            String name, Model model) {

        model.addAttribute("message", name);
        model.addAttribute("list", stringList);

        return "hello"; 
    }    

    @PostMapping("/")
	public String setName(@RequestParam String name, Model model) {
		
		if(name.equals("")){

			System.out.println("Empty String");
			return "redirect:/";

		} 
        message = name;
        model.addAttribute("message", name);
        model.addAttribute("list", stringList);
        
        //return "redirect:/";
		return "hello";

	}
    
}
