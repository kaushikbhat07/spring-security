package com.kb.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	@GetMapping(value = "/")
	public String home() {
		return "Hello home!";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "Hello admin!";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "Hello user!";
	}
}
