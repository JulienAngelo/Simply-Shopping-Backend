package com.devcrawlers.simply.shopping.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.Buyer;
import com.devcrawlers.simply.shopping.domain.Seller;
import com.devcrawlers.simply.shopping.domain.User;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.BuyerRepository;
import com.devcrawlers.simply.shopping.repository.UserRepository;
import com.devcrawlers.simply.shopping.resources.BuyerAddResource;
import com.devcrawlers.simply.shopping.resources.BuyerUpdateResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.BuyerService;

@Component
@Transactional(rollbackFor = Exception.class)
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private Environment environment;

	@Override
	public List<Buyer> getAll() {

		return buyerRepository.findAll();
	}

	@Override
	public Optional<Buyer> getById(Long id) {

		Optional<Buyer> isPresentBuyer = buyerRepository.findById(id);
		if (isPresentBuyer.isPresent()) {
			return Optional.ofNullable(isPresentBuyer.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Buyer> getByFirstName(String firstname) {

		return buyerRepository.findByFirstNameContaining(firstname);

	}

	@Override
	public List<Buyer> getByStatus(String status) {

		return buyerRepository.findByStatus(status);

	}

	@Override
	public Optional<Buyer> getByUserId(Long id) {

		Optional<Buyer> isPresentBuyer = buyerRepository.findByUserId(id);

		if (isPresentBuyer.isPresent()) {
			return Optional.ofNullable(isPresentBuyer.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Long addAndValidateBuyer(BuyerAddResource buyerAddResource) {

		Buyer buyer = new Buyer();

		Optional<Buyer> isPresentBuyer = buyerRepository.findByNic(buyerAddResource.getNic());
		if (isPresentBuyer.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		}

		Optional<User> user = userRepository.findById(Long.parseLong(buyerAddResource.getUserId()));
		if (!user.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "userId");
		} else {
			buyer.setUser(user.get());
		}

		buyer.setFirstName(buyerAddResource.getFirstName());
		buyer.setLastName(buyerAddResource.getLastName());
		buyer.setFullName(buyerAddResource.getFullName());
		buyer.setAddressLine1(buyerAddResource.getAddresLine1());
		buyer.setAddressLine2(buyerAddResource.getAddresLine2());
		buyer.setAddressLine3(buyerAddResource.getAddresLine3());
		buyer.setEmail(buyerAddResource.getEmail());
		buyer.setPhoneNumber(buyerAddResource.getMobile());
		buyer.setLandLine(buyerAddResource.getLandline());
		buyer.setNic(buyerAddResource.getNic());
		buyer.setDob(buyerAddResource.getDob());
		buyer.setStatus(buyerAddResource.getStatus());
		buyer.setCreatedUser(authTokenFilter.getUsername());
		buyer.setCreatedDate(getCreateOrModifyDate());
		buyer = buyerRepository.saveAndFlush(buyer);

		return buyer.getId();
	}

	@Override
	public Buyer updateAndValidateBuyer(Long id, BuyerUpdateResource buyerUpdateResource) {

		Optional<Buyer> isPresentBuyer = buyerRepository.findById(id);
		if(!isPresentBuyer.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		}
		
		Buyer buyer = isPresentBuyer.get();
		
		Optional<User> user = userRepository.findById(Long.parseLong(buyerUpdateResource.getUserId()));
		if(!user.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "userId");
		}
		else {
			buyer.setUser(user.get());
		}
		
		buyer.setFirstName(buyerUpdateResource.getFirstName());
		buyer.setLastName(buyerUpdateResource.getLastName());
		buyer.setFullName(buyerUpdateResource.getFullName());
		buyer.setAddressLine1(buyerUpdateResource.getAddresLine1());
		buyer.setAddressLine2(buyerUpdateResource.getAddresLine2());
		buyer.setAddressLine3(buyerUpdateResource.getAddresLine3());
		buyer.setEmail(buyerUpdateResource.getEmail());
		buyer.setPhoneNumber(buyerUpdateResource.getMobile());
		buyer.setLandLine(buyerUpdateResource.getLandline());
		buyer.setNic(buyerUpdateResource.getNic());
		buyer.setDob(buyerUpdateResource.getDob());
		buyer.setStatus(buyerUpdateResource.getStatus());
		buyer.setModifiedUser(authTokenFilter.getUsername());
		buyer.setModifiedDate(getCreateOrModifyDate());
		buyer = buyerRepository.saveAndFlush(buyer);
		
		return buyer;
	}

	private Timestamp getCreateOrModifyDate() {

		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		return new Timestamp(now.getTime());
	}

}
