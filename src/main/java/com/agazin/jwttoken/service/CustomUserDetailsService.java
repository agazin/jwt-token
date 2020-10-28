package com.agazin.jwttoken.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agazin.jwttoken.entity.UserAccount;
import com.agazin.jwttoken.repository.UserAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = repository.findByUserName(username);
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}