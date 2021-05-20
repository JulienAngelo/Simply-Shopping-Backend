package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Attributes;
import com.devcrawlers.simply.shopping.enums.CommonStatus;



/**
 * Attributes Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   08-05-2021   						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Long> {
	
	Optional <Attributes> findByName(String name);
	
	List <Attributes> findByStatus(CommonStatus status);
	
	Optional <Attributes> findByNameAndId(String name, Long id);
}
