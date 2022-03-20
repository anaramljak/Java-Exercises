package com.lab5.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lab5.demo.Models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

}
