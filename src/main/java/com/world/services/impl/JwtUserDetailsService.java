package com.world.services.impl;

import com.world.entity.Login;
import com.world.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	LoginRepository repository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Login user = repository.findByUserName(username)
				.orElseThrow(() ->
						new UsernameNotFoundException("User not found with username: " + username));

		String passEncoder = passwordEncoder.encode(user.getPassword());

		return new User(user.getUsername(),passEncoder, new ArrayList<>());

	}

}