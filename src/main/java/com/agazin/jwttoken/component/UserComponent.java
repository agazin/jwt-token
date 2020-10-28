package com.agazin.jwttoken.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.agazin.jwttoken.entity.UserAccount;
import com.agazin.jwttoken.repository.UserAccountRepository;

import org.springframework.security.core.userdetails.User;

@Component
public class UserComponent {
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Object getUserProfile() {
		Authentication authentication = getAuthentication();
		User user = (User)authentication.getPrincipal();
		String username = user.getUsername();
		UserAccount userAccount = userAccountRepository.findByUserName(username);
		return userAccount;
	}
}
