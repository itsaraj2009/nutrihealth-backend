package com.ecom.nutrihealth.repository;


import com.ecom.nutrihealth.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
