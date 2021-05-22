package com.devcrawlers.simply.shopping.controllers;

import java.math.BigDecimal;
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

import com.devcrawlers.simply.shopping.resources.DeliveyInfoRequestResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;

@RestController
@RequestMapping(value = "/delivey")
@CrossOrigin(origins = "*")
public class DummyDeliveryServiceController {
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/make")
	public ResponseEntity<Object> create(@Valid @RequestBody DeliveyInfoRequestResource deliveyInfoRequestResource){
		Random rnd = new Random();
	    int number = rnd.nextInt(99999999);
	    String s=String.valueOf(number);
	    BigDecimal max = new BigDecimal(199);
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec = actualRandomDec
                .setScale(2, BigDecimal.ROUND_DOWN);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("common.saved"), s);
		responseMessage.setDeliveryFee(actualRandomDec.toString());
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/status/{id}")
	public ResponseEntity<Object> getDeleveryStatusById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource responseMessage = new MessageResponseResource();
		String[] names = { "SHIPPED", "DELIVERD","TO BE SHIPPED","REJECTED" };
		String name = names[(int) (Math.random() * names.length)];
		responseMessage.setStatus(name);
		return new ResponseEntity<>(responseMessage,HttpStatus.OK);
	}

}
