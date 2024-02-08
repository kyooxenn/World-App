package com.world.services.impl;

import com.world.entity.Login;
import com.world.enums.ErrorType;
import com.world.exception.BaseException;
import com.world.repository.LoginRepository;
import com.world.request.JwtRequest;
import com.world.utils.SnowflakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.Optional;


@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Login user = loginRepository.findByUserName(username)
				.orElseThrow(() ->
						new UsernameNotFoundException("User not found with username: " + username));

		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());

	}


	public void register(JwtRequest loginRequest) {

		if(!ObjectUtils.isEmpty(loginRequest.getUsername()) && !ObjectUtils.isEmpty(loginRequest.getPassword())) {

			Optional<Login> user = loginRepository.findByUserName(loginRequest.getUsername());
			if (user.isPresent()) {
				throw BaseException.create(ErrorType.UserAlreadyExist);
			}

			Login newUser = new Login();
			long id =loginRepository.countRecord();
			SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(id);
			newUser.setId(idGenerator.nextId());
			newUser.setUsername(loginRequest.getUsername());
			newUser.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
			loginRepository.save(newUser);
		}

	};


}