package com.devcrawlers.simply.shopping.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.Buyer;
import com.devcrawlers.simply.shopping.repository.BuyerRepository;
import com.devcrawlers.simply.shopping.repository.UserRepository;
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

}
