package com.utmz.centreon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utmz.centreon.model.User;


public interface UserDao extends JpaRepository<User, Long> {

	User findByLogin(String login);
	User findByIpCentreon(String ipCentreon);

}