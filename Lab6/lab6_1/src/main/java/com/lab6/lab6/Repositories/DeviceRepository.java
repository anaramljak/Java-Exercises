package com.lab6.lab6.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lab6.lab6.Models.Device;
import com.lab6.lab6.Models.Measurements;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
	
	@Query("SELECT d.measurments FROM Device d WHERE d.id = :id")
	public List<Measurements> findMeasurements(@Param("id") String id,Pageable paging); 
}
