package com.lab6.lab6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lab6.lab6.Models.Client;
import com.lab6.lab6.Models.Measurements;
import com.lab6.lab6.Repositories.ClientRepository;

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

	public Long count() {
		return repository.count();
	}
}