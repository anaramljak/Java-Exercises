package com.lab5.demo.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lab5.demo.Models.Device;
@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

}
