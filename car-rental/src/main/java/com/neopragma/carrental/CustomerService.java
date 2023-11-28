package com.neopragma.carrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    /** called from unit tests */
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /** used by spring boot */
    CustomerService() {}

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer save(Customer newCustomer) {
        return repository.save(newCustomer);
    }

    public Customer replace(Customer newCustomer, Long id) {
        return repository.findById(id)
            .map(customer -> {
                customer.setName(newCustomer.getName());
                customer.setDateOfBirth(newCustomer.getDateOfBirth());
                customer.setSmoker(newCustomer.isSmoker());
                customer.setUraniumPlusMemberNumber(newCustomer.getUraniumPlusMemberNumber());
                customer.setUraniumPlusSince(newCustomer.getUraniumPlusSince());
                return repository.save(customer);
            })
            .orElseGet(() -> {
                newCustomer.setId(id);
                return repository.save(newCustomer);
            });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
