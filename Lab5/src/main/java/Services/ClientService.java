package com.lab5.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab5.demo.Models.Client;
import com.lab5.demo.Repositories.ClientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;

	public Client createClient(Client c) {
		return repository.save(c);
	}

	public List<Client> getClient() {
		return repository.findAll();
	}

	public void deleteClient(String id) {
		repository.deleteById(id);
	}

	public Client updateClient(String id, Client details) {
		Client c = repository.findById(id).get();
		return repository.save(c);
	}
}