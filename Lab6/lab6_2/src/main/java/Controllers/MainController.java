package com.lab6_2.lab6_2.Controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lab6_2.lab6_2.Models.Measurements;


@Controller
public class MainController {	

	@GetMapping("/devices")
	public String getData(@RequestParam String id,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "3") Integer pageSize,
			@RequestParam(required = false) String sortBy, 
			Model model) {	
		URI targetUrl= UriComponentsBuilder.fromUriString("http://localhost:8080/")
				.path("/device/measurements")
			    .queryParam("id", id)
			    .queryParam("page", page)
			    .queryParam("pageSize", pageSize)
			    .queryParam("sortBy", sortBy)
			    .build()
			    .toUri();
				
		RestTemplate restTemplate = new RestTemplate();
		Measurements[] arr = restTemplate.getForObject(targetUrl, Measurements[].class);
		List<Measurements> list= Arrays.asList(arr);
		model.addAttribute("list", list);
		return "list";
	}

	@GetMapping("/measure/add")
	public String showAddMeasure(Model model) {
	    Measurements m = new Measurements();
	    model.addAttribute("m", m); 
	    return "add";
	}

	@PostMapping("/measurements/add")
	public String addContact(Model model,
	        @ModelAttribute("m") Measurements m) {  
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity("http://localhost:8080/measurements", m, null);
	        return "list";         
	}

	
}
