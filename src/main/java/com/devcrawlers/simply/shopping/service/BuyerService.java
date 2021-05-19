package com.devcrawlers.simply.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devcrawlers.simply.shopping.domain.Buyer;

@Service
public interface BuyerService {

	public List<Buyer> getAll();

	public Optional<Buyer> getById(Long id);

	public List<Buyer> getByFirstName(String firstname);

	public List<Buyer> getByStatus(String status);

}
