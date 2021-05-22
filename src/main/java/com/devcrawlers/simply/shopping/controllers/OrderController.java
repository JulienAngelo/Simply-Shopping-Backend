package com.devcrawlers.simply.shopping.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcrawlers.simply.shopping.domain.Order;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.resources.OrderAddResource;
import com.devcrawlers.simply.shopping.resources.OrderUpdateResource;
import com.devcrawlers.simply.shopping.service.OrderService;

/**
 * Order Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/order")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * Gets the all orders.
	 *
	 * @return the all orders
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllOrders() {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		List<Order> order = orderService.findAll();
		if (!order.isEmpty()) {
			return new ResponseEntity<>((Collection<Order>) order, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the order by id.
	 *
	 * @param id - the id
	 * @return the order by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable(value = "id", required = true) Long id) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		Optional<Order> isPresentOrder = orderService.findById(id);
		if (isPresentOrder.isPresent()) {
			return new ResponseEntity<>(isPresentOrder, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the orders by status.
	 *
	 * @param status - the status
	 * @return the orders by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getOrdersByStatus(@PathVariable(value = "status", required = true) String status) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		List<Order> order = orderService.findByStatus(status);
		if (!order.isEmpty()) {
			return new ResponseEntity<>((Collection<Order>) order, HttpStatus.OK);
		} else {
			messageResponseResource.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(messageResponseResource, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addOrder(@Valid @RequestBody OrderAddResource orderAddResource) {
		Long orderId = orderService.saveAndValidateOrder(orderAddResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.saved"), orderId.toString());
		return new ResponseEntity<>(messageResponseResource, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update order.
	 *
	 * @param id - the id
	 * @param orderUpdateResource - the order update resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable(value = "id", required = true) Long id,
			@Valid @RequestBody OrderUpdateResource orderUpdateResource) {
		Long orderId = orderService.updateAndValidateOrder(id, orderUpdateResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.updated"), orderId.toString());
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
	
	
	/**
	 * Delete order.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id", required = true) Long id) {
		String message = orderService.deleteOrder(id);
		MessageResponseResource messageResponseResource = new MessageResponseResource(message);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
}
