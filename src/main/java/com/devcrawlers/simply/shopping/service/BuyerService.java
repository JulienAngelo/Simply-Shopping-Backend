package com.devcrawlers.simply.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devcrawlers.simply.shopping.domain.Buyer;
import com.devcrawlers.simply.shopping.domain.Seller;
import com.devcrawlers.simply.shopping.resources.BuyerAddResource;
import com.devcrawlers.simply.shopping.resources.BuyerUpdateResource;
import com.devcrawlers.simply.shopping.resources.SellerAddResource;
import com.devcrawlers.simply.shopping.resources.SellerUpdateResource;

@Service
public interface BuyerService {

	public List<Buyer> getAll();

	public Optional<Buyer> getById(Long id);

	public List<Buyer> getByFirstName(String firstname);

	public List<Buyer> getByStatus(String status);
	
	public Optional<Buyer> getByUserId(Long id);
	
	public Long addAndValidateBuyer(BuyerAddResource buyerAddResource);
	
	public Buyer updateAndValidateBuyer (Long id, BuyerUpdateResource buyerUpdateResource);

}
