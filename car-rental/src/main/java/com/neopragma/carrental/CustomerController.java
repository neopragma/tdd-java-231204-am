package com.neopragma.carrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	CollectionModel<EntityModel<Customer>> all() {

		List<EntityModel<Customer>> customers = customerService.findAll().stream()
				.map(customer -> EntityModel.of(customer,
						linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
						linkTo(methodOn(CustomerController.class).all()).withRel("customers")))
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}

	@PostMapping("/customer")
	Customer newCustomer(@RequestBody Customer newCustomer) {
		return customerService.save(newCustomer);
	}

	@GetMapping("/customer/{id}")
	EntityModel<Customer> one(@PathVariable("id") Long id) {
		Customer customer = customerService.findById(id);
		return EntityModel.of(customer,
				linkTo(methodOn(CustomerController.class).one(id)).withSelfRel(),
				linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
	}

	@PutMapping("/customer/{id}")
	Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
		return customerService.replace(newCustomer, id);
	}

	@DeleteMapping("/customer/{id}")
	void deleteCustomer(@PathVariable Long id) {
		customerService.deleteById(id);
	}
}
