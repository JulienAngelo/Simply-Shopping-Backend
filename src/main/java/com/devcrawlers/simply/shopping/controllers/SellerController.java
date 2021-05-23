package com.devcrawlers.simply.shopping.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.domain.Seller;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.resources.SellerAddResource;
import com.devcrawlers.simply.shopping.resources.SellerUpdateResource;
import com.devcrawlers.simply.shopping.service.SellerService;

@RestController
@RequestMapping(value = "/seller")
@CrossOrigin(origins = "*")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllSellers() {
		
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Seller> isPresentSeller = sellerService.getAll();
		
		if(!isPresentSeller.isEmpty()) {
			return new ResponseEntity<>((Collection<Seller>) isPresentSeller, HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getSellersById(@PathVariable(value = "id", required = true) Long id) {
		
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Seller> isPresentSeller = sellerService.getById(id);
		
		if(isPresentSeller.isPresent()) {
			return new ResponseEntity<>(isPresentSeller.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/first-name/{firstName}")
	public ResponseEntity<Object> getSellersByFirstName(@PathVariable(value = "firstName", required = true) String firstName) {
		
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Seller> isPresentSeller = sellerService.getByFirstName(firstName);
		
		if(!isPresentSeller.isEmpty()) {
			return new ResponseEntity<>((Collection<Seller>) isPresentSeller, HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<Object> getSellersByStatus(@PathVariable(value = "status", required = true) String status) {
		
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Seller> isPresentSeller = sellerService.getByStatus(status);
		
		if(!isPresentSeller.isEmpty()) {
			return new ResponseEntity<>((Collection<Seller>) isPresentSeller, HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/userId/{id}")
	public ResponseEntity<Object> getSellerByUserId(@PathVariable(value = "userId", required = true) Long id) {
		
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Seller> isPresentSeller = sellerService.getByUserId(id);
		
		if(isPresentSeller.isPresent()) {
			return new ResponseEntity<>(isPresentSeller.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> addSeller(@Valid @RequestBody SellerAddResource sellerAddResource) {
		
		Long sellerId = sellerService.addAndValidateSeller(sellerAddResource);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("common.saved"), sellerId.toString());
		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Object> updateSeller(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody SellerUpdateResource sellerUpdateResource) {
		
		Seller seller = sellerService.updateAndValidateSeller(id, sellerUpdateResource);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("common.updated"), seller);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
}
