package com.agazin.jwttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agazin.jwttoken.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
	UserAccount findByUserName(String username);
}
