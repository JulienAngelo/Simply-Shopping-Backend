package com.devcrawlers.simply.shopping.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devcrawlers.simply.shopping.domain.Item;
import com.devcrawlers.simply.shopping.resources.ItemAddResource;
import com.devcrawlers.simply.shopping.resources.ItemUpdateResource;

/**
 * Item Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface ItemService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Item> findAll();

	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Item> findById(Long id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Item> findByStatus(String status);
	
	
	/**
	 * Find by name.
	 *
	 * @param name - the name
	 * @return the list
	 */
	public List<Item> findByName(String name);
	
	
	/**
	 * Save and validate item.
	 *
	 * @param itemAddResource - the item add resource
	 * @return the long
	 */
	public Long saveAndValidateItem(ItemAddResource itemAddResource);
	
	
	/**
	 * Update and validate item.
	 *
	 * @param id - the id
	 * @param itemUpdateResource - the item update resource
	 * @return the item
	 */
	public Item updateAndValidateItem(Long id, ItemUpdateResource itemUpdateResource);
	

	/**
	 * Delete item.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteItem(Long id);
	
}
