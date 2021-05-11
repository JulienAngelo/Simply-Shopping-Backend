package com.devcrawlers.simply.shopping.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.domain.Attributes;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.resources.CommonRequestResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.service.AttributesService;
	

/**
 * Attributes Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                             MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/attributes")
@CrossOrigin(origins = "*")
public class AttributesController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AttributesService attributesService;
	
	/*
	 * get all Attributes
	 * @param @PathVariable{tenantId}
	 * @param @PathVariable{all}
	 * @return List
	 **/
	@GetMapping("/all")
	public ResponseEntity<Object> getAllAttributes(){
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Attributes>isPresentAttributes = attributesService.getAll();
		if(!isPresentAttributes.isEmpty()) {
			return new ResponseEntity<>((Collection<Attributes>)isPresentAttributes,HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get Attributes by ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getAttributesById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Attributes> isPresentAttributes = attributesService.getById(id);
		if(isPresentAttributes.isPresent()) {
			return new ResponseEntity<>(isPresentAttributes.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get Attributes by name
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {name}
	 * @return List
	 **/
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getAttributesByName(@PathVariable(value = "name", required = true) String name){
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Attributes> isPresentAttributes = attributesService.getByName(name);
		if(isPresentAttributes.isPresent()) {
			return new ResponseEntity<>(isPresentAttributes.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get Attributes by status
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {status}
	 * @return List
	 **/
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getAttributesByStatus(@PathVariable(value = "status", required = true) String status){
		MessageResponseResource responseMessage = new MessageResponseResource();
		if(status.equals(CommonStatus.ACTIVE.toString()) || status.equals(CommonStatus.INACTIVE.toString())) {
			List<Attributes> isPresentAttributes = attributesService.getByStatus(status);
			if(!isPresentAttributes.isEmpty()) {
				return new ResponseEntity<>(isPresentAttributes, HttpStatus.OK);
			}
			else {
				responseMessage.setMessage(environment.getProperty("common.record-not-found"));
				return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
			}
		}
		else {
			responseMessage.setMessage(environment.getProperty("common-status.pattern"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
     * save Attributes
     * @param @PathVariable{tenantId}
     * @param @RequestBody{CommonAddResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> addAttributes(@Valid @RequestBody CommonRequestResource commonAddResource){
		Attributes Attributes = attributesService.addAttributes(commonAddResource);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("common.saved"), Long.toString(Attributes.getId()));
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	/**
     * update Attributes
     * @param @PathVariable{tenantId}
     * @param @RequestBody{CommonUpdateResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PutMapping(value = "update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updateAttributes(@PathVariable(value = "id", required = true) Long id, 
												                 @Valid @RequestBody CommonRequestResource commonUpdateResource){
		MessageResponseResource successAndErrorDetailsResource=new MessageResponseResource();
		Optional<Attributes>isPresentAttributes = attributesService.getById(id);		
		if(isPresentAttributes.isPresent()) {
			commonUpdateResource.setId(id.toString());
			Attributes Attributes = attributesService.updateAttributes(commonUpdateResource);
			successAndErrorDetailsResource = new MessageResponseResource(environment.getProperty("common.updated"), Attributes.getId().toString());
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * get Attributes by ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 **/
	@DeleteMapping(value = "/id/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteAttributesById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource successAndErrorDetailsResource=new MessageResponseResource();
		Optional<Attributes>isPresentAttributes = attributesService.getById(id);		
		if(isPresentAttributes.isPresent()) {
			attributesService.deleteAttributes(id);
			successAndErrorDetailsResource = new MessageResponseResource(environment.getProperty("common.deleted"), id.toString());
			return new ResponseEntity<>(successAndErrorDetailsResource,HttpStatus.OK);
		}
		else {
			successAndErrorDetailsResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
}
