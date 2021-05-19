package com.devcrawlers.simply.shopping.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.Attributes;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.AttributesRepository;
import com.devcrawlers.simply.shopping.resources.CommonRequestResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.AttributesService;




/**
 * Attributes Service Impl
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   08-05-2021   						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class AttributesServiceImpl implements AttributesService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AttributesRepository attributesRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Override
	public List<Attributes> getAll() {
		return attributesRepository.findAll();
	}

	@Override
	public Optional<Attributes> getById(Long id) {
		Optional<Attributes> isPresentAttributes = attributesRepository.findById(id);
		if (isPresentAttributes.isPresent()) {
			return Optional.ofNullable(isPresentAttributes.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Attributes> getByName(String name) {
		Optional<Attributes> isPresentAttributes = attributesRepository.findByName(name);
		if (isPresentAttributes.isPresent()) {
			return Optional.ofNullable(isPresentAttributes.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Attributes> getByStatus(String status) {
		return attributesRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public Attributes addAttributes(CommonRequestResource commonAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        
        Optional<Attributes> isPresentAttributes = attributesRepository.findByName(commonAddResource.getName());
        if (isPresentAttributes.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		}
        
        Attributes attributes = new Attributes();
        attributes.setName(commonAddResource.getName());
        attributes.setStatus(CommonStatus.valueOf(commonAddResource.getStatus()));
        attributes.setCreatedDate(currentTimestamp);
        attributes.setCreatedUser(authTokenFilter.getUsername());
        attributes = attributesRepository.save(attributes);
    	return attributes;
	}

	@Override
	public Attributes updateAttributes(CommonRequestResource AttributesUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		Optional<Attributes> isPresentAttributes = attributesRepository.findById(Long.parseLong(AttributesUpdateResource.getId()));
		if (!isPresentAttributes.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		
		Optional<Attributes> isPresentAttributesName = attributesRepository.findByName(AttributesUpdateResource.getName());
		if (isPresentAttributesName.isPresent() && isPresentAttributesName.get().getId() != isPresentAttributes.get().getId())			
			throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		
		Attributes attributes = isPresentAttributes.get();
		attributes.setName(AttributesUpdateResource.getName());
		attributes.setStatus(CommonStatus.valueOf(AttributesUpdateResource.getStatus()));
		attributes.setModifiedDate(currentTimestamp);
		attributes.setModifiedUser(authTokenFilter.getUsername());
		attributes = attributesRepository.saveAndFlush(attributes);
    	return attributes;
	}

	@Override
	public void deleteAttributes(Long id) {
		Optional<Attributes> isPresentAttributes = attributesRepository.findById(id);
		if (!isPresentAttributes.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		else
			attributesRepository.deleteById(id);
	}
	
}
