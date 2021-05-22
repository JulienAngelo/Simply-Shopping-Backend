package com.devcrawlers.simply.shopping.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.resources.DummyPaymentRequestResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;

@RestController
@RequestMapping(value = "/payment")
@CrossOrigin(origins = "*")
public class DummyPaymentServiceController {
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/pay")
	public ResponseEntity<Object> pay(@Valid @RequestBody DummyPaymentRequestResource dummyPaymentRequestResource){
		Random rnd = new Random();
	    int number = rnd.nextInt(99999999);
	    String s=String.valueOf(number);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("payment.sucess"));
		responseMessage.setRefrenceNo(s);
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}

}
