package com.devcrawlers.simply.shopping.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.Category;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.CategoryRepository;
import com.devcrawlers.simply.shopping.resources.CommonRequestResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.CategoryService;


/**
 * Category Service Impl
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   09-07-2020       						  MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getById(Long id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (isPresentCategory.isPresent()) {
			return Optional.ofNullable(isPresentCategory.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Category> getByName(String name) {
		Optional<Category> isPresentCategory = categoryRepository.findByName(name);
		if (isPresentCategory.isPresent()) {
			return Optional.ofNullable(isPresentCategory.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Category> getByStatus(String status) {
		return categoryRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public Category addCategory(CommonRequestResource commonAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        Optional<Category> isPresentCategory = categoryRepository.findByName(commonAddResource.getName());
        if (isPresentCategory.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		}
        
        Category category = new Category();
        category.setName(commonAddResource.getName());
        category.setStatus(CommonStatus.valueOf(commonAddResource.getStatus()));
        category.setCreatedDate(currentTimestamp);
        category.setCreatedUser(authTokenFilter.getUsername());
        category = categoryRepository.save(category);
    	return category;
	}

	@Override
	public Category updateCategory(CommonRequestResource commonUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		Optional<Category> isPresentCategory = categoryRepository.findById(Long.parseLong(commonUpdateResource.getId()));
		if (!isPresentCategory.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		
		Optional<Category> isPresentCategoryName = categoryRepository.findByName(commonUpdateResource.getName());
		if (isPresentCategoryName.isPresent() && isPresentCategoryName.get().getId() != isPresentCategory.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		
		Category category = isPresentCategory.get();
		category.setName(commonUpdateResource.getName());
		category.setStatus(CommonStatus.valueOf(commonUpdateResource.getStatus()));
		category.setModifiedDate(currentTimestamp);
		category.setModifiedUser(authTokenFilter.getUsername());
		category = categoryRepository.saveAndFlush(category);
    	return category;
	}

	@Override
	public void deleteCategory(Long id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (!isPresentCategory.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		else
			categoryRepository.deleteById(id);	
	}
	
}
