package com.neopragma.carrental.controller;

import com.neopragma.carrental.service.CustomerService;
import com.neopragma.carrental.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping("/customer")
	public CollectionModel<EntityModel<Customer>> all() {
		List<EntityModel<Customer>> customers = service.findAll().stream()
				.map(customer -> EntityModel.of(customer,
						linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
						linkTo(methodOn(CustomerController.class).all()).withRel("customers")))
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}

	@PostMapping("/customer")
	public Customer newCustomer(@RequestBody Customer newCustomer) {
		return service.save(newCustomer);
	}

	@GetMapping("/customer/{id}")
	public EntityModel<Customer> one(@PathVariable("id") Long id) {
		Customer customer = service.findById(id);
		return EntityModel.of(customer,
				linkTo(methodOn(CustomerController.class).one(id)).withSelfRel(),
				linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
	}

	@PutMapping("/customer/{id}")
	public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
		return service.replace(newCustomer, id);
	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		service.deleteById(id);
	}
}
