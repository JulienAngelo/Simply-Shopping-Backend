package com.devcrawlers.simply.shopping.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.domain.AttributeValue;
import com.devcrawlers.simply.shopping.domain.Buyer;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.service.BuyerService;

@RestController
@RequestMapping(value = "/buyer")
@CrossOrigin(origins = "*")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private Environment environment;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllBuyers() {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Buyer> isPresentBuyer = buyerService.getAll();
		if (!isPresentBuyer.isEmpty()) {
			return new ResponseEntity<>((Collection<Buyer>) isPresentBuyer, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBuyerById(@PathVariable(value = "id", required = true) Long id) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Buyer> isPresentBuyer = buyerService.getById(id);
		if (isPresentBuyer.isPresent()) {
			return new ResponseEntity<>(isPresentBuyer.get(), HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/first-name/{firstName}")
	public ResponseEntity<Object> getBuyersByFirstName(
			@PathVariable(value = "firstName", required = true) String firstName) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Buyer> isPresentBuyer = buyerService.getByFirstName(firstName);
		if (!isPresentBuyer.isEmpty()) {
			return new ResponseEntity<>((Collection<Buyer>) isPresentBuyer, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<Object> getBuyersByStatus(@PathVariable(value = "status", required = true) String status) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Buyer> isPresentBuyer = buyerService.getByStatus(status);
		if (!isPresentBuyer.isEmpty()) {
			return new ResponseEntity<>((Collection<Buyer>) isPresentBuyer, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}

}
