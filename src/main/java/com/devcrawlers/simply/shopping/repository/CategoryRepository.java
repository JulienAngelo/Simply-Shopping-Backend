package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Category;
import com.devcrawlers.simply.shopping.enums.CommonStatus;


/**
 * Category Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   08-05-2021   						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional <Category> findByName(String name);
	
	List <Category> findByStatus(CommonStatus status);
	
	Optional <Category> findByNameAndId(String name, Long id);

	Optional<Category> findByIdAndStatus(Long id, CommonStatus status);
}
