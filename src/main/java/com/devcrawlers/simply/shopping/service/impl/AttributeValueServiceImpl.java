package com.devcrawlers.simply.shopping.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.AttributeValue;
import com.devcrawlers.simply.shopping.domain.Attributes;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.AttributeValueRepository;
import com.devcrawlers.simply.shopping.repository.AttributesRepository;
import com.devcrawlers.simply.shopping.resources.AttributeValueRequestResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.AttributeValueService;



/**
 * AttributeValue Service Impl
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   08-04-2021       						  MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class AttributeValueServiceImpl implements AttributeValueService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AttributeValueRepository attributeValueRepository;
	
	@Autowired
	private AttributesRepository attributesRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Override
	public List<AttributeValue> getAll() {
		return attributeValueRepository.findAll();
	}

	@Override
	public Optional<AttributeValue> getById(Long id) {
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findById(id);
		if (isPresentAttributeValue.isPresent()) {
			return Optional.ofNullable(isPresentAttributeValue.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<AttributeValue> getByValue(String value) {
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findByName(value);
		if (isPresentAttributeValue.isPresent()) {
			return Optional.ofNullable(isPresentAttributeValue.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<AttributeValue> getByStatus(String status) {
		return attributeValueRepository.findByStatus(CommonStatus.valueOf(status));
	}

	@Override
	public AttributeValue addAttributeValue(AttributeValueRequestResource attributeValueAddResource) {
		Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
//        Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findByName(attributeValueAddResource.getName());
//        if (isPresentAttributeValue.isPresent()) {
//        	throw new ValidateRecordException(environment.getProperty("common.unique"), "name");
//		}
        
        Optional<Attributes> isPresentAttributes = attributesRepository.findById(Long.parseLong(attributeValueAddResource.getAttributesId()));
        if (!isPresentAttributes.isPresent())
        	throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributesId");
        if(isPresentAttributes.get().getStatus().equals(CommonStatus.INACTIVE))
        	throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributesId");
        
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setAttributes(isPresentAttributes.get());
        attributeValue.setName(attributeValueAddResource.getName());
        attributeValue.setStatus(CommonStatus.valueOf(attributeValueAddResource.getStatus()));
        attributeValue.setCreatedDate(currentTimestamp);
        attributeValue.setCreatedUser(authTokenFilter.getUsername());
        attributeValue = attributeValueRepository.save(attributeValue);
    	return attributeValue;
	}

	@Override
	public AttributeValue updateAttributeValue(AttributeValueRequestResource attributeValueUpdateResource) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findById(Long.parseLong(attributeValueUpdateResource.getId()));
		if (!isPresentAttributeValue.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		
		Optional<AttributeValue> isPresentAttributeValueName = attributeValueRepository.findByName(attributeValueUpdateResource.getName());
		if (isPresentAttributeValueName.isPresent() && isPresentAttributeValueName.get().getId() != isPresentAttributeValue.get().getId())			
			throw new ValidateRecordException("common.unique", "value");
		
        Optional<Attributes> isPresentAttributes = attributesRepository.findById(Long.parseLong(attributeValueUpdateResource.getAttributesId()));
        if (!isPresentAttributes.isPresent())
        	throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributesId");
        if(isPresentAttributes.get().getStatus().equals(CommonStatus.INACTIVE))
        	throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributesId");
		
		AttributeValue attributeValue = isPresentAttributeValue.get();
		
		attributeValue.setName(attributeValueUpdateResource.getName());
		attributeValue.setAttributes(isPresentAttributes.get());
		attributeValue.setStatus(CommonStatus.valueOf(attributeValueUpdateResource.getStatus()));
		attributeValue.setModifiedDate(currentTimestamp);
		attributeValue.setModifiedUser(authTokenFilter.getUsername());
		attributeValue = attributeValueRepository.saveAndFlush(attributeValue);
    	return attributeValue;
	}

	@Override
	public void deleteAttributeValue(Long id) {
		Optional<AttributeValue> isPresentAttributeValue = attributeValueRepository.findById(id);
		if (!isPresentAttributeValue.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		else
			attributeValueRepository.deleteById(id);	
	}

	@Override
	public List<AttributeValue> getByAttributeId(Long id) {
		return  attributeValueRepository.findByAttributesId(id);
	}
	
}
