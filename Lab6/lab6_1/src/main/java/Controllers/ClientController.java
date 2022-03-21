package com.lab6.lab6.Controllers;

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

import com.lab6.lab6.ErrorWrapper.DataNotEnteredException;
import com.lab6.lab6.ErrorWrapper.DataNotFoundException;
import com.lab6.lab6.Models.Client;
import com.lab6.lab6.Services.ClientService;

@RestController
class ClientController {
	
	@Autowired
	private ClientService service;

	@PostMapping("/client")
	public ResponseEntity<Client> createClient(@RequestBody Client c) throws DataNotEnteredException {
		if (c == null)
			throw new DataNotEnteredException("Data for client not entered");
		else
			return new ResponseEntity<>(service.createClient(c), HttpStatus.OK);
	}

	@GetMapping("/client")
	public ResponseEntity<List<Client>> getClients() {
		return new ResponseEntity<>(service.getClient(), HttpStatus.OK);
	}

	@PutMapping("/client/{Id}")
	public ResponseEntity<Client> updateClient(@PathVariable(value = "Id") String id, @RequestBody Client details) throws DataNotFoundException, DataNotEnteredException {
		if (id == null)
			throw new DataNotFoundException("Id is null");
		else if(details == null)
			throw new DataNotEnteredException("Data for client not entered");
		else
			return new ResponseEntity<>(service.updateClient(id, details), HttpStatus.OK);
	}

	@DeleteMapping("/client/{Id}")
	public ResponseEntity<Client> deleteClient(@PathVariable(value = "Id") String id) throws DataNotFoundException {
		if (id == null)
			throw new DataNotFoundException("Id is null");
		else {
			service.deleteClient(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
