package com.challenge.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.challenge.domain.Users;
import com.challenge.domain.UsersRepository;

public class DetailsService implements UserDetailsService {

	private final PasswordEncoder bcryptEncoder;
	private final UsersRepository repo;

	public DetailsService(UsersRepository repo, PasswordEncoder bcryptEncoder) {
		this.repo = repo;
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
}