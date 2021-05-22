package com.devcrawlers.simply.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.repository.BrandRepository;
import com.devcrawlers.simply.shopping.repository.BuyerRepository;
import com.devcrawlers.simply.shopping.repository.CategoryRepository;
import com.devcrawlers.simply.shopping.repository.ItemRepository;
import com.devcrawlers.simply.shopping.repository.OrderRepository;
import com.devcrawlers.simply.shopping.resources.DashboardResponse;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.DashboardService;

/**
 * Dashboard Service Implementation
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
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private Environment environment;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	
	@Override
	public DashboardResponse getDashboardDetails() {
		
		DashboardResponse dashboardResponse = new DashboardResponse();
		
		Long totalItems = itemRepository.count();
		Long totalCategories = categoryRepository.count();
		Long totalBrands = brandRepository.count();
		Long totalSellers = Long.valueOf(0);
		Long totalBuyers = buyerRepository.count();
		Long totalOrders = orderRepository.count();
		
		dashboardResponse.setTotalItems(totalItems.toString());
		dashboardResponse.setTotalCategories(totalCategories.toString());
		dashboardResponse.setTotalBrands(totalBrands.toString());
		dashboardResponse.setTotalSellers(totalSellers.toString());	
		dashboardResponse.setTotalBuyers(totalBuyers.toString());
		dashboardResponse.setTotalOrders(totalOrders.toString());
		
		return dashboardResponse;
	}

}
