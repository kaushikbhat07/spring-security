package com.kb.springsecurity;

import com.kb.springsecurity.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// User credentials to authenticate
	@Autowired
	// UserDetailsService userDetailsService;
	MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);

		// Set your authorization configuration
		// Order: Most restrictive to least
		http.csrf().disable().authorizeRequests()
				.antMatchers("/authenticate").permitAll()
				.anyRequest().authenticated();

//		http.authorizeRequests()
//				.antMatchers("/admin").authenticated()
//				.antMatchers("/user").authenticated()
//				.antMatchers("/").permitAll()
//				.and().formLogin();
	}
}