package com.utmz.centreon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utmz.centreon.dao.UserDao;
import com.utmz.centreon.model.User;
@Transactional
@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	ApiService apiService;
	
	@Autowired 
	CryptageService cryptageService;
	
	public boolean checkExist(User userFromAppli) {
	boolean trouver=false;
	User userFromBase=userDao.findByLogin(userFromAppli.getLogin());
	if(userFromBase != null)
		{
			if(userFromBase.getPassword().equals(userFromAppli.getPassword()))
			{
				trouver =true;
			}			
		}
	return trouver;
	}
	
	public boolean createUser(User newUser) {
	boolean trouver=false;
	if(userDao.findByLogin(newUser.getLogin())==null) {
		if(newUser.getLoginCentreon()!=null && newUser.getIpCentreon()!=null && newUser.getPassword()!=null && newUser.getPasswordCentreon()!=null)
		if(apiService.checkCentreonExist(newUser)) {
			String mdpEncrypt = cryptageService.encrypt(newUser.getPasswordCentreon());
			newUser.setPasswordCentreon(mdpEncrypt);
			userDao.save(newUser);
			trouver=true;
		}	
	}
	return trouver;
	}
}

