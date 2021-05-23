package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Order;

/**
 * Order Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByStatus(String status);
	
	public Optional<Order> findByBuyerIdAndPaidStatus(Long buyerId, String paidStatus);

	public Boolean existsByBuyerIdAndPaidStatus(Long buyerId, String paidStatus);

	public Optional<Order> findByIdAndStatus(Long id, String status);
	
}
