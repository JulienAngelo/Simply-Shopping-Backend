package com.devcrawlers.simply.shopping.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.Seller;
import com.devcrawlers.simply.shopping.domain.User;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.SellerRepository;
import com.devcrawlers.simply.shopping.repository.UserRepository;
import com.devcrawlers.simply.shopping.resources.SellerAddResource;
import com.devcrawlers.simply.shopping.resources.SellerUpdateResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.SellerService;

@Component
@Transactional(rollbackFor = Exception.class)
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private Environment environment;
	
	@Override
	public List<Seller> getAll() {
		
		return sellerRepository.findAll();
	}
	
	@Override
	public Optional<Seller> getById(Long id) {
		
		Optional<Seller> isPresentSeller = sellerRepository.findById(id);
		
		if(isPresentSeller.isPresent()) {
			return Optional.ofNullable(isPresentSeller.get());
		}
		else {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Seller> getByFirstName(String firstName) {
		
		return sellerRepository.findByFirstNameContaining(firstName);
	}
	
	@Override
	public List<Seller> getByStatus(String status) {
		
		return sellerRepository.findByStatus(status);
	}
	
	@Override
	public Optional<Seller> getByUserId(Long id) {
		
		Optional<Seller> isPresentSeller = sellerRepository.findByUserId(id);
		
		if(isPresentSeller.isPresent()) {
			return Optional.ofNullable(isPresentSeller.get());
		}
		else {
			return Optional.empty();
		}
	}
	
	@Override
	public Long addAndValidateSeller(SellerAddResource sellerAddResource) {
		
		Seller seller = new Seller();
		
		Optional<Seller> isPresentSeller = sellerRepository.findByNic(sellerAddResource.getNic());
		if(isPresentSeller.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		}
		
		Optional<User> user = userRepository.findById(Long.parseLong(sellerAddResource.getUserId()));
		if(!user.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "userId");
		}
		else {
			seller.setUser(user.get());
		}
		
		seller.setFirstName(sellerAddResource.getFirstName());
		seller.setLastName(sellerAddResource.getLastName());
		seller.setFullName(sellerAddResource.getFullName());
		seller.setAddressLine1(sellerAddResource.getAddresLine1());
		seller.setAddressLine2(sellerAddResource.getAddresLine2());
		seller.setAddressLine3(sellerAddResource.getAddresLine3());
		seller.setEmail(sellerAddResource.getEmail());
		seller.setMobile(sellerAddResource.getMobile());
		seller.setLandline(sellerAddResource.getLandline());
		seller.setNic(sellerAddResource.getNic());
		seller.setDob(sellerAddResource.getDob());
		seller.setStatus(sellerAddResource.getStatus());
		seller.setCreatedUser(authTokenFilter.getUsername());
		seller.setCreatedDate(getCreateOrModifyDate());
		seller = sellerRepository.saveAndFlush(seller);
		
		return seller.getId();
	}
	
	public Seller updateAndValidateSeller(Long id, SellerUpdateResource sellerUpdateResource) {
		
		Optional<Seller> isPresentSeller = sellerRepository.findById(id);
		if(!isPresentSeller.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		}
		
		Seller seller = isPresentSeller.get();
		
		Optional<User> user = userRepository.findById(Long.parseLong(sellerUpdateResource.getUserId()));
		if(!user.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "userId");
		}
		else {
			seller.setUser(user.get());
		}
		
		seller.setFirstName(sellerUpdateResource.getFirstName());
		seller.setLastName(sellerUpdateResource.getLastName());
		seller.setFullName(sellerUpdateResource.getFullName());
		seller.setAddressLine1(sellerUpdateResource.getAddresLine1());
		seller.setAddressLine2(sellerUpdateResource.getAddresLine2());
		seller.setAddressLine3(sellerUpdateResource.getAddresLine3());
		seller.setEmail(sellerUpdateResource.getEmail());
		seller.setMobile(sellerUpdateResource.getMobile());
		seller.setLandline(sellerUpdateResource.getLandline());
		seller.setNic(sellerUpdateResource.getNic());
		seller.setDob(sellerUpdateResource.getDob());
		seller.setStatus(sellerUpdateResource.getStatus());
		seller.setModifiedUser(authTokenFilter.getUsername());
		seller.setModifiedDate(getCreateOrModifyDate());
		seller = sellerRepository.saveAndFlush(seller);
		
		return seller;
	}
	
	private Timestamp getCreateOrModifyDate() {
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		return new Timestamp(now.getTime());
	}
}
