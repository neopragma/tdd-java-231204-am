package com.neopragma.carrental.persistence;

import com.neopragma.carrental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
