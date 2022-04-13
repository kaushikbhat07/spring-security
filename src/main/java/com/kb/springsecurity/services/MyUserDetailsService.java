package com.kb.springsecurity.services;

import com.kb.springsecurity.models.MyUserDetails;
import com.kb.springsecurity.models.User;
import com.kb.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);

		if (user.isPresent())
			return new MyUserDetails(user.get());

		throw new UsernameNotFoundException("Not found: " + userName);
//		return new User("username", "password", new ArrayList<>());
	}
}
