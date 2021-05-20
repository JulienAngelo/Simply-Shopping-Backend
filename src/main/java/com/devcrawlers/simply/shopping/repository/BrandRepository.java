package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Brand;
import com.devcrawlers.simply.shopping.enums.CommonStatus;



/**
 * Brand Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Story Point   Task No    Author       Description
 *-------------------------------------------------------------------------------------------------------
 *    1   08-05-2021   						   MenukaJ        Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	Optional <Brand> findByName(String name);
	
	List <Brand> findByStatus(CommonStatus status);
	
	Optional <Brand> findByNameAndId(String name, Long id);

	Optional<Brand> findByIdAndStatus(Long id, CommonStatus status);
}
