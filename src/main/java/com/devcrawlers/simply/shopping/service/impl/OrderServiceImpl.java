package com.devcrawlers.simply.shopping.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.core.IdGenerator;
import com.devcrawlers.simply.shopping.domain.Buyer;
import com.devcrawlers.simply.shopping.domain.Item;
import com.devcrawlers.simply.shopping.domain.Order;
import com.devcrawlers.simply.shopping.domain.OrderItem;
import com.devcrawlers.simply.shopping.enums.ActionType;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.enums.PaymentStatus;
import com.devcrawlers.simply.shopping.enums.ServiceEntity;
import com.devcrawlers.simply.shopping.exception.InvalidDetailListServiceIdException;
import com.devcrawlers.simply.shopping.exception.NoRecordFoundException;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.BuyerRepository;
import com.devcrawlers.simply.shopping.repository.ItemRepository;
import com.devcrawlers.simply.shopping.repository.OrderItemRepository;
import com.devcrawlers.simply.shopping.repository.OrderRepository;
import com.devcrawlers.simply.shopping.resources.OrderAddResource;
import com.devcrawlers.simply.shopping.resources.OrderItemAddResource;
import com.devcrawlers.simply.shopping.resources.OrderUpdateResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.OrderService;

/**
 * Order Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private Environment environment;

	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findById(Long id) {
		Optional<Order> isPresentOrder = orderRepository.findById(id);
		if (isPresentOrder.isPresent()) {
			Order order = isPresentOrder.get();
			List<OrderItem> orderItems = orderItemRepository.findByOrderId(isPresentOrder.get().getId());
			order.setItemList(orderItems);
			return isPresentOrder;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Order> findByStatus(String status) {
		List<Order> orders = orderRepository.findByStatus(status);
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItems = new ArrayList<>();

		for (Order order : orders) {
			orderItems = orderItemRepository.findByOrderId(order.getId());
			order.setItemList(orderItems);
			orderList.add(order);
		}
		return orderList;
	}

	@Override
	public Long saveAndValidateOrder(OrderAddResource orderAddResource) {
		
		Order order = new Order();
		
		Optional<Buyer> buyer = buyerRepository.findByIdAndStatus(Long.parseLong(orderAddResource.getBuyersId()), CommonStatus.ACTIVE.toString());
		if (!buyer.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "buyersId");
		} else {
			order.setBuyer(buyer.get());
		}
		
		order.setServiceChargeRate(new BigDecimal(orderAddResource.getServiceChargeRate()));
		order.setServiceCharge(new BigDecimal(orderAddResource.getServiceCharge()));
		order.setVatChargeRate(new BigDecimal(orderAddResource.getVatChargeRate()));
		order.setVatCharge(new BigDecimal(orderAddResource.getVatCharge()));
		order.setNetAmount(new BigDecimal(orderAddResource.getNetAmount()));
		order.setPaidStatus(PaymentStatus.PAID.toString());
		order.setPaymentRefNo("REF00" + generateNo());
		order.setPaymentDate(getCreateOrModifyDate());
		order.setDeliveryFlag(orderAddResource.getDeliveryFlag());
		order.setStatus(orderAddResource.getStatus());
		order.setCreatedUser(authTokenFilter.getUsername());
		order.setCreatedDate(getCreateOrModifyDate());
		order = orderRepository.saveAndFlush(order);
		
		if(orderAddResource.getItemList() !=null && !orderAddResource.getItemList().isEmpty()) {			
			Integer index=0;
			
			for(OrderItemAddResource orderItemAddResource : orderAddResource.getItemList()) {
				
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(order);
				
				Optional<Item> item = itemRepository.findByIdAndStatus(Long.parseLong(orderItemAddResource.getItemsId()), CommonStatus.ACTIVE.toString());
				if (!item.isPresent()) {
					throw new InvalidDetailListServiceIdException(environment.getProperty("common.invalid-value"), ServiceEntity.ITEM_ID, ActionType.ORDER_ITEM_SAVE, index);
				}
					
				if(orderItemRepository.existsByOrderIdAndItemId(order.getId(), Long.parseLong(orderItemAddResource.getItemsId()))) {
					throw new InvalidDetailListServiceIdException(environment.getProperty("common.unique"), ServiceEntity.ITEM_ID, ActionType.ORDER_ITEM_SAVE, index);
				} else {
					orderItem.setItem(item.get());
				}
				
				orderItem.setQuantity(Long.parseLong(orderItemAddResource.getQuantity()));
				orderItem.setAmount(new BigDecimal(orderItemAddResource.getAmount()));
				orderItem.setStatus(orderItemAddResource.getStatus());
				orderItem.setCreatedUser(authTokenFilter.getUsername());
				orderItem.setCreatedDate(getCreateOrModifyDate());
				orderItemRepository.saveAndFlush(orderItem);
				
				index++;
			}
		}
		
		return order.getId();
		
	}

	@Override
	public Long updateAndValidateOrder(Long id, OrderUpdateResource orderUpdateResource) {
		
		return null;
	}

	@Override
	public String deleteOrder(Long id) {
		Optional<Order> isPresentOrder = orderRepository.findById(id);
		if (!isPresentOrder.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
		if(!orderItems.isEmpty()) {
			orderItemRepository.deleteByOrderId(id);
		}
		
		orderRepository.deleteById(id);
		
		return environment.getProperty("common.deleted-id") + id;
	}

	private long generateNo() {
		List<Order> orderList = orderRepository.findAll();
		List<Long> orderIdList = new ArrayList<>();
		
		for(Order orderObject : orderList) {
			orderIdList.add(orderObject.getId());
		}
		
		return IdGenerator.generateRefNos(orderIdList);	
	}
	
	private Timestamp getCreateOrModifyDate() {
		Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	return new Timestamp(now.getTime());
	}
	
}
