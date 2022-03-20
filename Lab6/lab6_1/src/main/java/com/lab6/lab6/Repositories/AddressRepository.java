package com.lab6.lab6.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.lab6.lab6.Models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

}
