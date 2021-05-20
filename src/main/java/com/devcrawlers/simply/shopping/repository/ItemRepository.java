package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Item;

/**
 * Item Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	public List<Item> findByNameContaining(String name);

	public List<Item> findByStatus(String status);

	public Optional<Item> findByIdAndName(Long id, String name);

	public Optional<Item> findByName(String name);
	
	public Optional<Item> findByNameAndIdNotIn(String name, Long id);
	
}