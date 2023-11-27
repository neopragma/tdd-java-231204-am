package com.neopragma.carrental.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting() {
		return new Greeting(counter.incrementAndGet(), String.format(template, "World"));
	}
	@GetMapping("/greeting/{name}")
	public Greeting greeting(@PathVariable("name") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
