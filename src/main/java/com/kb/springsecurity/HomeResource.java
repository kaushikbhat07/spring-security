package com.kb.springsecurity;

import com.kb.springsecurity.models.AuthenticationRequest;
import com.kb.springsecurity.models.AuthenticationResponse;
import com.kb.springsecurity.models.MyUserDetails;
import com.kb.springsecurity.services.MyUserDetailsService;
import com.kb.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping(value = "/")
	public String home() {
		return "Hello world!";
	}

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello there!";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "Hello admin!";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "Hello user!";
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = myUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
