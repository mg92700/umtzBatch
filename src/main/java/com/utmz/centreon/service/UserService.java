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
	String tokePhoneId = userFromAppli.getTokenPhoneId();
	User userFromBase=userDao.findByLogin(userFromAppli.getLogin());

	if(userFromBase != null)
		{
			if(userFromBase.getPassword().equals(userFromAppli.getPassword()))
			{
				userFromBase.setTokenPhoneId(tokePhoneId);
				userDao.save(userFromBase);
				trouver =true;
			}			
		}
	return trouver;
	}
	
	public Integer createUser(User newUser) {
	int status = -1;
	if(userDao.findByLogin(newUser.getLogin())==null) {
		if(newUser.getLoginCentreon()!=null && newUser.getIpCentreon()!=null && newUser.getPassword()!=null && newUser.getPasswordCentreon()!=null)
		if(apiService.checkCentreonExist(newUser)) {
			String mdpEncrypt = cryptageService.encrypt(newUser.getPasswordCentreon());
			newUser.setPasswordCentreon(mdpEncrypt);
			newUser.setTokenPhoneId(null);
			userDao.save(newUser);
			status=0;
		}
		else {
			status = -2;
		}
	}
	return status;
	}
}

