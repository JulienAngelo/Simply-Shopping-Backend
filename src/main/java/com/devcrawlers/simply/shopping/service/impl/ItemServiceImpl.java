package com.devcrawlers.simply.shopping.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devcrawlers.simply.shopping.domain.AttributeValue;
import com.devcrawlers.simply.shopping.domain.Brand;
import com.devcrawlers.simply.shopping.domain.Category;
import com.devcrawlers.simply.shopping.domain.Item;
import com.devcrawlers.simply.shopping.enums.CommonStatus;
import com.devcrawlers.simply.shopping.exception.NoRecordFoundException;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.repository.AttributeValueRepository;
import com.devcrawlers.simply.shopping.repository.BrandRepository;
import com.devcrawlers.simply.shopping.repository.CategoryRepository;
import com.devcrawlers.simply.shopping.repository.ItemRepository;
import com.devcrawlers.simply.shopping.resources.ItemAddResource;
import com.devcrawlers.simply.shopping.resources.ItemUpdateResource;
import com.devcrawlers.simply.shopping.security.jwt.AuthTokenFilter;
import com.devcrawlers.simply.shopping.service.ItemService;

/**
 * Item Service Implementation
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
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private Environment environment;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private AttributeValueRepository attributeValueRepository;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Optional<Item> findById(Long id) {
		Optional<Item> item = itemRepository.findById(id);
		if (item.isPresent()) {
			return Optional.ofNullable(item.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Item> findByStatus(String status) {
		return itemRepository.findByStatus(status);
	}

	@Override
	public List<Item> findByName(String name) {
		return itemRepository.findByNameContaining(name);
	}
	
	@Override
	public List<Item> findByCategoryIdAndStatus(Long categoryId, String status) {
		return itemRepository.findByCategoryIdAndStatus(categoryId, status);
	}

	@Override
	public Long saveAndValidateItem(ItemAddResource itemAddResource) {
		Item item = new Item();
		
		Optional<Item> isPresentItem = itemRepository.findByName(itemAddResource.getName());
        if (isPresentItem.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		}
		
		Optional<Category> category = categoryRepository.findByIdAndStatus(Long.parseLong(itemAddResource.getCategorysId()), CommonStatus.ACTIVE);
		if (!category.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "categorysId");
		} else {
			item.setCategory(category.get());
		}
		
		Optional<Brand> brand = brandRepository.findByIdAndStatus(Long.parseLong(itemAddResource.getBrandsId()), CommonStatus.ACTIVE);
		if (!brand.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "brandsId");
		} else {
			item.setBrand(brand.get());
		}
		
		if (itemAddResource.getAttributeValueId1() != null && !itemAddResource.getAttributeValueId1().isEmpty()) {
			Optional<AttributeValue> attributeValue1 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemAddResource.getAttributeValueId1()), CommonStatus.ACTIVE);
			if (!attributeValue1.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId1");
			} else {
				item.setAttributeValue1(attributeValue1.get());
			}
		}
		
		if (itemAddResource.getAttributeValueId2() != null && !itemAddResource.getAttributeValueId2().isEmpty()) {
			Optional<AttributeValue> attributeValue2 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemAddResource.getAttributeValueId2()), CommonStatus.ACTIVE);
			if (!attributeValue2.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId2");
			} else {
				item.setAttributeValue2(attributeValue2.get());
			}
		}
		
		if (itemAddResource.getAttributeValueId3() != null && !itemAddResource.getAttributeValueId3().isEmpty()) {
			Optional<AttributeValue> attributeValue3 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemAddResource.getAttributeValueId3()), CommonStatus.ACTIVE);
			if (!attributeValue3.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId3");
			} else {
				item.setAttributeValue3(attributeValue3.get());
			}
		}
		
		if (itemAddResource.getAttributeValueId4() != null && !itemAddResource.getAttributeValueId4().isEmpty()) {
			Optional<AttributeValue> attributeValue4 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemAddResource.getAttributeValueId4()), CommonStatus.ACTIVE);
			if (!attributeValue4.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId4");
			} else {
				item.setAttributeValue4(attributeValue4.get());
			}
		}
		
		item.setName(itemAddResource.getName());
		item.setDescription(itemAddResource.getDescription());
		if (itemAddResource.getQuantity() != null && !itemAddResource.getQuantity().isEmpty()) {
			item.setQuantity(Long.parseLong(itemAddResource.getQuantity()));
		} else {
			item.setQuantity(Long.valueOf(0));
		}
		item.setUrl1(itemAddResource.getUrl1());
		item.setUrl2(itemAddResource.getUrl2());
		item.setUrl3(itemAddResource.getUrl3());
		item.setUrl4(itemAddResource.getUrl4());
		item.setPrice(new BigDecimal(itemAddResource.getPrice()));
		item.setDiscount(new BigDecimal(itemAddResource.getDiscount()));
		item.setStatus(itemAddResource.getStatus());
		item.setCreatedUser(authTokenFilter.getUsername());
		item.setCreatedDate(getCreateOrModifyDate());
		item = itemRepository.saveAndFlush(item);
		return item.getId();
	}
	

	@Override
	public Item updateAndValidateItem(Long id, ItemUpdateResource itemUpdateResource) {
		
		Optional<Item> isPresentItem = itemRepository.findById(id);
		if (!isPresentItem.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		}
		
		Optional<Item> isPresentItemByName = itemRepository.findByNameAndIdNotIn(itemUpdateResource.getName(), id);
		if (isPresentItemByName.isPresent())
			throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
		
		Item item = isPresentItem.get();
		
		Optional<Category> category = categoryRepository.findByIdAndStatus(Long.parseLong(itemUpdateResource.getCategorysId()), CommonStatus.ACTIVE);
		if (!category.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "categorysId");
		} else {
			item.setCategory(category.get());
		}
		
		Optional<Brand> brand = brandRepository.findByIdAndStatus(Long.parseLong(itemUpdateResource.getBrandsId()), CommonStatus.ACTIVE);
		if (!brand.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "brandsId");
		} else {
			item.setBrand(brand.get());
		}
		
		if (itemUpdateResource.getAttributeValueId1() != null && !itemUpdateResource.getAttributeValueId1().isEmpty()) {
			Optional<AttributeValue> attributeValue1 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemUpdateResource.getAttributeValueId1()), CommonStatus.ACTIVE);
			if (!attributeValue1.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId1");
			} else {
				item.setAttributeValue1(attributeValue1.get());
			}
		}
		
		if (itemUpdateResource.getAttributeValueId2() != null && !itemUpdateResource.getAttributeValueId2().isEmpty()) {
			Optional<AttributeValue> attributeValue2 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemUpdateResource.getAttributeValueId2()), CommonStatus.ACTIVE);
			if (!attributeValue2.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId2");
			} else {
				item.setAttributeValue2(attributeValue2.get());
			}
		}
		
		if (itemUpdateResource.getAttributeValueId3() != null && !itemUpdateResource.getAttributeValueId3().isEmpty()) {
			Optional<AttributeValue> attributeValue3 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemUpdateResource.getAttributeValueId3()), CommonStatus.ACTIVE);
			if (!attributeValue3.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId3");
			} else {
				item.setAttributeValue3(attributeValue3.get());
			}
		}
		
		if (itemUpdateResource.getAttributeValueId4() != null && !itemUpdateResource.getAttributeValueId4().isEmpty()) {
			Optional<AttributeValue> attributeValue4 = attributeValueRepository.findByIdAndStatus(Long.parseLong(itemUpdateResource.getAttributeValueId4()), CommonStatus.ACTIVE);
			if (!attributeValue4.isPresent()) {
				throw new ValidateRecordException(environment.getProperty("common.invalid-value"), "attributeValueId4");
			} else {
				item.setAttributeValue4(attributeValue4.get());
			}
		}
		
		item.setName(itemUpdateResource.getName());
		item.setDescription(itemUpdateResource.getDescription());
		item.setQuantity(Long.parseLong(itemUpdateResource.getQuantity()));
		item.setUrl1(itemUpdateResource.getUrl1());
		item.setUrl2(itemUpdateResource.getUrl2());
		item.setUrl3(itemUpdateResource.getUrl3());
		item.setUrl4(itemUpdateResource.getUrl4());
		item.setPrice(new BigDecimal(itemUpdateResource.getPrice()));
		item.setDiscount(new BigDecimal(itemUpdateResource.getDiscount()));
		item.setStatus(itemUpdateResource.getStatus());
		item.setCreatedUser(authTokenFilter.getUsername());
		item.setCreatedDate(getCreateOrModifyDate());
		item = itemRepository.saveAndFlush(item);
		return item;
	}

	@Override
	public String deleteItem(Long id) {
		Optional<Item> isPresentItem = itemRepository.findById(id);
		if (!isPresentItem.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		itemRepository.deleteById(id);
		return environment.getProperty("common.deleted-id") + id;
	}
	
	private Timestamp getCreateOrModifyDate() {
		Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	return new Timestamp(now.getTime());
	}
	
}
