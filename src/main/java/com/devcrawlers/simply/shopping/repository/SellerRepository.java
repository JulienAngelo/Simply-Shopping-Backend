package com.devcrawlers.simply.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcrawlers.simply.shopping.domain.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

	public List<Seller> findByFirstNameContaining(String firstName);
	
	public List<Seller> findByStatus(String status);
	
	public Optional<Seller> findByNic(String nic);
	
	public Optional<Seller> findByUserId(Long id);
}