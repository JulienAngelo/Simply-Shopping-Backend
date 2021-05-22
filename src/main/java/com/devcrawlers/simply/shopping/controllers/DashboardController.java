package com.devcrawlers.simply.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.resources.DashboardResponse;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.service.DashboardService;

/**
 * Dashboard Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                             MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private DashboardService dashboardService;
	
	
	@GetMapping("/get")
	public ResponseEntity<Object> getDashboardDetails() {
		MessageResponseResource responseMessage = new MessageResponseResource();
		DashboardResponse dashboardResponse = dashboardService.getDashboardDetails();
		if (dashboardResponse != null) {
			return new ResponseEntity<>(dashboardResponse, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
}
