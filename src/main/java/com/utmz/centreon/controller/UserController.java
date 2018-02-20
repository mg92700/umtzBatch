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
import com.utmz.centreon.dto.UserDto;
import com.utmz.centreon.model.User;
import com.utmz.centreon.service.ApiService;




@RestController
@RequestMapping(value = "/api")
public class UserController {
	
	
	@Autowired
	JTransfo JTransfo;
	
	
	@Autowired
	UserDao userDao;
	
	
	
	
	@RequestMapping(value = "/verifUserExist", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public boolean checkExist(@RequestBody UserDto unUser)
	{
		boolean trouver=false;
		//Jtransfo permet de recuperer les données en post passé en parametre
		User userFromAppli  = (User) JTransfo.convert(unUser);
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
