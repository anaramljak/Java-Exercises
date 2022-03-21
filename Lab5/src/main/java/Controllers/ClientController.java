package com.lab5.demo.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lab5.demo.Models.Client;
import com.lab5.demo.Repositories.ClientRepository;
import com.lab5.demo.Services.ClientService;

@RestController
class ClientController {
	
	@Autowired
	private ClientService service;

	@PostMapping("/client")
	public ResponseEntity<Client> createClient(@RequestBody Client c) {
		if (c == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.createClient(c), HttpStatus.OK);
	}

	@GetMapping("/client")
	public ResponseEntity<List<Client>> getClients() {
		return new ResponseEntity<>(service.getClient(), HttpStatus.OK);
	}

	@PutMapping("/client/{Id}")
	public ResponseEntity<Client> updateClient(@PathVariable(value = "Id") String id, @RequestBody Client details) {
		if (id == null || details == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.updateClient(id, details), HttpStatus.OK);
	}

	@DeleteMapping("/client/{Id}")
	public ResponseEntity<Client> deleteClient(@PathVariable(value = "Id") String id) {
		if (id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else {
			service.deleteClient(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
