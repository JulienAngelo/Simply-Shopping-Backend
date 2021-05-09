package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.AttributeValue;
import com.devcrawlers.simply.shopping.enums.CommonStatus;



/**
 * AttributeValue Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   08-05-2021   						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
	
	List <AttributeValue> findByStatus(CommonStatus status);
	
	Optional <AttributeValue> findByNameAndId(String value, Long id);

	Optional<AttributeValue> findByName(String value);

	List<AttributeValue> findByAttributesId(Long id);
}
