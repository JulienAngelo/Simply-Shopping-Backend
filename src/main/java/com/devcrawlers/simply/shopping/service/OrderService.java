package com.devcrawlers.simply.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devcrawlers.simply.shopping.domain.Order;
import com.devcrawlers.simply.shopping.resources.BuyerResponse;
import com.devcrawlers.simply.shopping.resources.OrderAddResource;
import com.devcrawlers.simply.shopping.resources.OrderItemAddResource;
import com.devcrawlers.simply.shopping.resources.OrderUpdateResource;

/**
 * Order Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface OrderService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Order> findAll();

	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Order> findById(Long id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Order> findByStatus(String status);
	
	
	/**
	 * Find by buyer id and paid status.
	 *
	 * @param buyerId - the buyer id
	 * @param paidStatus - the paid status
	 * @return the optional
	 */
	public Optional<Order> findByBuyerIdAndPaidStatus(Long buyerId, String paidStatus);
	
	
	/**
	 * Check buyer has orders.
	 *
	 * @param buyerId - the buyer id
	 * @param paidStatus - the paid status
	 * @return the buyer response
	 */
	public BuyerResponse checkBuyerHasOrders(Long buyerId, String paidStatus);
	
	
	/**
	 * Save and validate order.
	 *
	 * @param orderAddResource - the order add resource
	 * @return the long
	 */
	public Long saveAndValidateOrder(OrderAddResource orderAddResource);
	
	
	/**
	 * Update and validate order.
	 *
	 * @param id - the id
	 * @param orderUpdateResource - the order update resource
	 * @return the long
	 */
	public Long updateAndValidateOrder(Long id, OrderUpdateResource orderUpdateResource);
	
	
	/**
	 * Save and validate order item.
	 *
	 * @param orderId - the order id
	 * @param orderItemAddResource - the order item add resource
	 * @return the long
	 */
	public Long saveAndValidateOrderItem(Long orderId, OrderItemAddResource orderItemAddResource);
	
	
	/**
	 * Delete order.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteOrder(Long id);
	
	
	/**
	 * Delete order item.
	 *
	 * @param orderItemId - the order item id
	 * @return the string
	 */
	public String deleteOrderItem(Long orderItemId);
	
}
