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

import com.devcrawlers.simply.shopping.domain.Item;
import com.devcrawlers.simply.shopping.resources.ItemAddResource;
import com.devcrawlers.simply.shopping.resources.ItemUpdateResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.service.ItemService;

/**
 * Item Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/item")
@CrossOrigin(origins = "*")
public class ItemController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * Gets the all items.
	 *
	 * @return the all items
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllItems() {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		List<Item> item = itemService.findAll();
		if (!item.isEmpty()) {
			return new ResponseEntity<>((Collection<Item>) item, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Gets the item by id.
	 *
	 * @param id - the id
	 * @return the item by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getItemById(@PathVariable(value = "id", required = true) Long id) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		Optional<Item> isPresentItem = itemService.findById(id);
		if (isPresentItem.isPresent()) {
			return new ResponseEntity<>(isPresentItem, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Gets the items by status.
	 *
	 * @param status - the status
	 * @return the items by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getItemsByStatus(@PathVariable(value = "status", required = true) String status) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		List<Item> item = itemService.findByStatus(status);
		if (!item.isEmpty()) {
			return new ResponseEntity<>((Collection<Item>) item, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
		
	/**
	 * Gets the items by name.
	 *
	 * @param name - the name
	 * @return the items by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getItemsByName(@PathVariable(value = "name", required = true) String name) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		List<Item> item = itemService.findByName(name);
		if (!item.isEmpty()) {
			return new ResponseEntity<>((Collection<Item>) item, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Adds the item.
	 *
	 * @param itemAddResource - the item add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addItem(@Valid @RequestBody ItemAddResource itemAddResource) {
		Long itemId = itemService.saveAndValidateItem(itemAddResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.saved"), itemId.toString());
		return new ResponseEntity<>(messageResponseResource, HttpStatus.CREATED);
	}
	
	/**
	 * Update item.
	 *
	 * @param id - the id
	 * @param itemUpdateResource - the item update resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateItem(@PathVariable(value = "id", required = true) Long id,
			@Valid @RequestBody ItemUpdateResource itemUpdateResource) {
		Item item = itemService.updateAndValidateItem(id, itemUpdateResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.updated"), item);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
	
	/**
	 * Delete item.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteItem(@PathVariable(value = "id", required = true) Long id) {
		String message = itemService.deleteItem(id);
		MessageResponseResource messageResponseResource = new MessageResponseResource(message);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.CREATED);
	}
	
}
