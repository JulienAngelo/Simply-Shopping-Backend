package com.devcrawlers.simply.shopping.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(value = "/validate/{cardNO}")
	public ResponseEntity<Object> getDeleveryStatusById(@PathVariable(value = "id", required = true) Long cardNO){
		MessageResponseResource responseMessage = new MessageResponseResource();
		String[] names = { "VALID", "INVALID" };
		String name = names[(int) (Math.random() * names.length)];
		String[] banks = { "hnb", "boc", "sampath" };
		String bank = names[(int) (Math.random() * names.length)];
		responseMessage.setStatus(name);
		return new ResponseEntity<>(responseMessage,HttpStatus.OK);
	}
	
	@PostMapping("/hnb")
	public ResponseEntity<Object> payHnb(@Valid @RequestBody DummyPaymentRequestResource dummyPaymentRequestResource){
		Random rnd = new Random();
	    int number = rnd.nextInt(99999999);
	    String s=String.valueOf(number);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("payment.sucess"));
		responseMessage.setRefrenceNo(s);
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	@PostMapping("/boc")
	public ResponseEntity<Object> payBoc(@Valid @RequestBody DummyPaymentRequestResource dummyPaymentRequestResource){
		Random rnd = new Random();
	    int number = rnd.nextInt(99999999);
	    String s=String.valueOf(number);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("payment.sucess"));
		responseMessage.setRefrenceNo(s);
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	@PostMapping("/sampath")
	public ResponseEntity<Object> paySampath(@Valid @RequestBody DummyPaymentRequestResource dummyPaymentRequestResource){
		Random rnd = new Random();
	    int number = rnd.nextInt(99999999);
	    String s=String.valueOf(number);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("payment.sucess"));
		responseMessage.setRefrenceNo(s);
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}

}
