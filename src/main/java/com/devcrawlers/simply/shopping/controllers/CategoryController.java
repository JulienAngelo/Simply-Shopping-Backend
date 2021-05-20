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

import com.devcrawlers.simply.shopping.domain.Category;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.resources.CommonRequestResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.service.CategoryService;
	

/**
 * Category Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-08-2020                             MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*")
public class CategoryController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * get all category
	 * @param @PathVariable{all}
	 * @return List
	 **/
	@GetMapping("/all")
	public ResponseEntity<Object> getAllCategory(){
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Category>isPresentCategory = categoryService.getAll();
		if(!isPresentCategory.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>)isPresentCategory,HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get category by ID
	 * @param @pathVariable {id}
	 * @return Optional
	 **/
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Category> isPresentCategory = categoryService.getById(id);
		if(isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get category by name
	 * @param @pathVariable {name}
	 * @return List
	 **/
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getCategoryByName(@PathVariable(value = "name", required = true) String name){
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Category> isPresentCategory = categoryService.getByName(name);
		if(isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
		}
		else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * get category by status
	 * @param @pathVariable {status}
	 * @return List
	 **/
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getCategoryByStatus(@PathVariable(value = "status", required = true) String status){
		MessageResponseResource responseMessage = new MessageResponseResource();
		if(status.equals(CommonStatus.ACTIVE.toString()) || status.equals(CommonStatus.INACTIVE.toString())) {
			List<Category> isPresentCategory = categoryService.getByStatus(status);
			if(!isPresentCategory.isEmpty()) {
				return new ResponseEntity<>(isPresentCategory, HttpStatus.OK);
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
     * save category
     * @param @RequestBody{CommonAddResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PostMapping("/add")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody CommonRequestResource commonAddResource){
		Category Category = categoryService.addCategory(commonAddResource);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("common.saved"), Long.toString(Category.getId()));
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	/**
     * update category
     * @param @RequestBody{CommonUpdateResource}
     * @return SuccessAndErrorDetailsDto
     */
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable(value = "id", required = true) Long id, 
												                 @Valid @RequestBody CommonRequestResource commonUpdateResource){
		MessageResponseResource MessageResponseResource=new MessageResponseResource();
		Optional<Category>isPresentCategory = categoryService.getById(id);		
		if(isPresentCategory.isPresent()) {
			commonUpdateResource.setId(id.toString());
			Category category = categoryService.updateCategory(commonUpdateResource);
			MessageResponseResource = new MessageResponseResource(environment.getProperty("common.updated"), category.getId().toString());
			return new ResponseEntity<>(MessageResponseResource,HttpStatus.OK);
		}
		else {
			MessageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(MessageResponseResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	/**
	 * get category by ID
	 * @param @pathVariable {id}
	 **/
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<Object> deleteCategoryById(@PathVariable(value = "id", required = true) Long id){
		MessageResponseResource MessageResponseResource=new MessageResponseResource();
		Optional<Category>isPresentCategory = categoryService.getById(id);		
		if(isPresentCategory.isPresent()) {
			categoryService.deleteCategory(id);
			MessageResponseResource = new MessageResponseResource(environment.getProperty("common.deleted"), id.toString());
			return new ResponseEntity<>(MessageResponseResource,HttpStatus.OK);
		}
		else {
			MessageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(MessageResponseResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
}
