package com.utmz.centreon.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.utmz.centreon.dao.UserDao;
import com.utmz.centreon.dto.CentreonDto;
import com.utmz.centreon.dto.UserDto;
import com.utmz.centreon.model.User;
import com.utmz.centreon.service.ApiService;

@RestController
//@RequestMapping(value = "/api")
public class CentreonController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/centreon", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<CentreonDto> centreon()
	{
		List<CentreonDto> centreonList = apiService.CentreonApi();
		return centreonList;
	}
	
	@RequestMapping(value = "/verif", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public boolean checkExist(@RequestBody User userFromAppli)
	{
//		Long i = (long) 1;
//		UserDto unUser = new UserDto(i,"ydays", "Ydays2018", "188.166.65.62", "admin", "Ydays2018");
//		User userFromAppli  = (User) JTransfo.convert(unUser);
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
	


	
}
