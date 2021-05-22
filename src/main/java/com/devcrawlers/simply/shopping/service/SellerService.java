package com.devcrawlers.simply.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devcrawlers.simply.shopping.domain.Seller;
import com.devcrawlers.simply.shopping.resources.SellerAddResource;
import com.devcrawlers.simply.shopping.resources.SellerUpdateResource;

@Service
public interface SellerService {

	public List<Seller> getAll();
	
	public Optional<Seller> getById(Long id);
	
	public List<Seller> getByFirstName(String firstName);
	
	public List<Seller> getByStatus(String status);
	
	public Optional<Seller> getByUserId(Long id);
	
	public Long addAndValidateSeller(SellerAddResource sellerAddResource);
	
	public Seller updateAndValidateSeller (Long id, SellerUpdateResource sellerUpdateResource);
}
