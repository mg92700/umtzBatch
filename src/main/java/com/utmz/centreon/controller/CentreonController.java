package com.utmz.centreon.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utmz.centreon.dao.UserDao;
import com.utmz.centreon.dto.CentreonDto;
import com.utmz.centreon.dto.CentreonServiceDto;
import com.utmz.centreon.dto.UserCheckServiceDto;
import com.utmz.centreon.model.User;
import com.utmz.centreon.service.ApiService;
import com.utmz.centreon.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class CentreonController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/centreon", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public List<CentreonDto> centreon(@RequestBody User userFromAppli)
	{
		List<CentreonDto> centreonList = null;
		if(userFromAppli.getLogin()!= null) {
		User userFromBase =  userDao.findByLogin(userFromAppli.getLogin());
		if(userFromBase != null) {
		centreonList = apiService.CentreonApi(userFromBase);
		}
		}
		return centreonList;
	}
	
	@RequestMapping(value = "/verifUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public boolean checkExist(@RequestBody User userFromAppli)
	{
		boolean trouver=false;
		
		if(userFromAppli.getLogin()!= null)
		{
		trouver = userService.checkExist(userFromAppli);
		}
	
		return trouver;	
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Integer createUser(@RequestBody User newUser)
	{
		int status = -1;
		if(newUser.getLogin()!= null)
		{
			status = userService.createUser(newUser);
		}
	
		return status;	
	}

	@RequestMapping(value = "/checkService", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<CentreonServiceDto> checkService(@RequestBody UserCheckServiceDto newUser)
	{
		User userFromBase = userDao.findByLogin(newUser.getLogin());
		List<CentreonServiceDto> serviceList = apiService.ServiceCentreon(userFromBase, newUser.getIdHost());
		return serviceList;
	}
	
	@RequestMapping(value = "/deconnexion", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public void test(@RequestBody User newUser)
	{
		User userFromBase = userDao.findByLogin(newUser.getLogin());
		userFromBase.setTokenPhoneId(null);
		userDao.save(userFromBase);
	}
	
	
}
