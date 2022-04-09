package com.kb.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	@GetMapping(value = "/home")
	public String home() {
		return "Hello there!";
	}
}
