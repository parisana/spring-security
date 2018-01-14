/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.demo.spring_security.controller;

import com.demo.spring_security.config.CustomUserDetails;
import com.demo.spring_security.domain.User;
import com.demo.spring_security.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Rob Winch
 * @author Joe Grandja
 */
@RestController
public class MyRestController {
	private final UserRepo userRepository;

	@Autowired
	public MyRestController(UserRepo userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/principal")
	public ResponseEntity<User> currentPrincipal(@AuthenticationPrincipal CustomUserDetails currentUser) {
		Optional<User> optionalUser = userRepository.findById(currentUser.getId());
		if (optionalUser.isPresent())
			return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
		else throw new UsernameNotFoundException("Something went wrong! User not found!");
	}

}