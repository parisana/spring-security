package com.demo.spring_security.config;

import com.demo.spring_security.domain.User;
import com.demo.spring_security.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepo userRepo;

	public UserDetailsServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    log.debug("***Called loadUserByUsername");
		Optional<User> optionalUser = userRepo.findByEmail(username);
		if (!optionalUser.isPresent())
			throw new UsernameNotFoundException(username+ " not found!");
		else return new CustomUserDetails(optionalUser.get());
	}
}
