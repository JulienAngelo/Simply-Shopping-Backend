package com.devcrawlers.simply.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	public List<Buyer> findByFirstNameContaining(String firstname);
	
	public List<Buyer> findByStatus(String status);

}
