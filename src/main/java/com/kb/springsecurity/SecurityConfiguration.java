package com.kb.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// super.configure(auth);

		// Set your configuration on the auth object
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("ADMIN")
				.and()
				.withUser("kaushik")
				.password("kaushik")
				.roles("USER");
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
		http.authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/").permitAll()
				.and().formLogin();
	}
}