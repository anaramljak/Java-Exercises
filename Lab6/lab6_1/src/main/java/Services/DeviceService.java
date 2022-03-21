package com.lab6.lab6.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lab6.lab6.Models.Device;
import com.lab6.lab6.Models.Measurements;
import com.lab6.lab6.Repositories.DeviceRepository;

@Service
public class DeviceService {
	@Autowired
	private DeviceRepository repository;

	public Device createDevice(Device c) {
		return repository.save(c);
	}

	public List<Device> getDevices() {
		return repository.findAll();
	}

	public void deleteDevice(String id) {
		repository.deleteById(id);
	}
	
	public Device updateDevice(String id, Device details) {
		Device d = repository.findById(id).get();
		d.setClient(d.getClient());
		d.setMeasurments(d.getMeasurments());
		return repository.save(d);
	}
	
	public List<Measurements> getData(String id,Integer page, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
		return repository.findMeasurements(id,paging);
	}
	
    public Long count() {
        return repository.count();
    }
}
