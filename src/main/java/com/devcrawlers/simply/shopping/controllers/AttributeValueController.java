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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.domain.AttributeValue;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.resources.AttributeValueRequestResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.service.AttributeValueService;
	

/**
 * AttributeValue Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                             MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/attribute-value")
@CrossOrigin(origins = "*")
public class AttributeValueController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AttributeValueService attributeValueService;
	
	/*
	 * get all AttributeValue
	 * @param @PathVariable{tenantId}
	 * @param @PathVariable{all}
	 * @return List
	 **/
	@GetMapping("/all")
	public ResponseEntity<Object> getAllAttributeValue(){
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<AttributeValue>isPresentAttributeValue = attributeValueService.getAll();
		if(!isPresentAttributeValue.isEmpty()) {
			return new ResponseEntity<>((Collection<AttributeValue>)isPresentAttributeValue,HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get AttributeValue by ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getAttributeValueById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<AttributeValue> isPresentAttributeValue = attributeValueService.getById(id);
		if(isPresentAttributeValue.isPresent()) {
			return new ResponseEntity<>(isPresentAttributeValue.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get AttributeValue by value
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {value}
	 * @return List
	 **/
	@GetMapping(value = "/value/{value}")
	public ResponseEntity<Object> getAttributeValueByName(@PathVariable(value = "value", required = true) String value){
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<AttributeValue> isPresentAttributeValue = attributeValueService.getByValue(value);
		if(isPresentAttributeValue.isPresent()) {
			return new ResponseEntity<>(isPresentAttributeValue.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get AttributeValue by status
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {status}
	 * @return List
	 **/
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getAttributeValueByStatus(@PathVariable(value = "status", required = true) String status){
		MessageResponseResource responseMessage = new MessageResponseResource();
		if(status.equals(CommonStatus.ACTIVE.toString()) || status.equals(CommonStatus.INACTIVE.toString())) {
			List<AttributeValue> isPresentAttributeValue = attributeValueService.getByStatus(status);
			if(!isPresentAttributeValue.isEmpty()) {
				return new ResponseEntity<>(isPresentAttributeValue, HttpStatus.OK);
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
     * save AttributeValue
     * @param @PathVariable{tenantId}
     * @param @RequestBody{AttributeValueAddResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PostMapping("/add")
	public ResponseEntity<Object> addAttributeValue(@Valid @RequestBody AttributeValueRequestResource attributeValueAddResource){
		AttributeValue attributeValue = attributeValueService.addAttributeValue(attributeValueAddResource);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("common.saved"), Long.toString(attributeValue.getId()));
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	/**
     * update AttributeValue
     * @param @PathVariable{tenantId}
     * @param @RequestBody{CommonUpdateResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Object> updateAttributeValue(@PathVariable(value = "id", required = true) Long id, 
												                 @Valid @RequestBody AttributeValueRequestResource attributeValueUpdateResource){
		MessageResponseResource MessageResponseResource=new MessageResponseResource();
		Optional<AttributeValue>isPresentAttributeValue = attributeValueService.getById(id);		
		if(isPresentAttributeValue.isPresent()) {
			attributeValueUpdateResource.setId(id.toString());
			AttributeValue attributeValue = attributeValueService.updateAttributeValue(attributeValueUpdateResource);
			MessageResponseResource = new MessageResponseResource(environment.getProperty("common.updated"), attributeValue.getId().toString());
			return new ResponseEntity<>(MessageResponseResource,HttpStatus.OK);
		}
		else {
			MessageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(MessageResponseResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * get AttributeValue by ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 **/
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<Object> deleteAttributeValueById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource MessageResponseResource=new MessageResponseResource();
		Optional<AttributeValue>isPresentAttributeValue = attributeValueService.getById(id);		
		if(isPresentAttributeValue.isPresent()) {
			attributeValueService.deleteAttributeValue(id);
			MessageResponseResource = new MessageResponseResource(environment.getProperty("common.deleted"), id.toString());
			return new ResponseEntity<>(MessageResponseResource,HttpStatus.OK);
		}
		else {
			MessageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(MessageResponseResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * get AttributeValue by Attribute ID
	 * @param @pathVariable{tenantId}
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/attribute-id/{id}")
	public ResponseEntity<Object> getAttributeValueByAttributeId(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<AttributeValue>isPresentAttributeValue = attributeValueService.getByAttributeId(id);
		if(!isPresentAttributeValue.isEmpty()) {
			return new ResponseEntity<>((Collection<AttributeValue>)isPresentAttributeValue,HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
}
