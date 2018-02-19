package com.utmz.centreon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utmz.centreon.dto.CentreonDto;
import com.utmz.centreon.service.ApiService;

@RestController
@RequestMapping(value = "/api")
public class CentreonController {



	@Autowired
	ApiService apiService;
	
	@RequestMapping(value = "/centreon", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<CentreonDto> centreon()
	{
		List<CentreonDto> centreonList = apiService.CentreonApi();
		return centreonList;
	}
	


	
}
