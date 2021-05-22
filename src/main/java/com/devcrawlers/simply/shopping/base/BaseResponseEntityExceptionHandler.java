package com.devcrawlers.simply.shopping.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devcrawlers.simply.shopping.enums.ActionType;
import com.devcrawlers.simply.shopping.exception.InvalidDetailListServiceIdException;
import com.devcrawlers.simply.shopping.exception.NoRecordFoundException;
import com.devcrawlers.simply.shopping.exception.ValidateRecordException;
import com.devcrawlers.simply.shopping.resources.AttributeValueRequestResource;
import com.devcrawlers.simply.shopping.resources.CommonRequestResource;
import com.devcrawlers.simply.shopping.resources.DeliveyInfoRequestResource;
import com.devcrawlers.simply.shopping.resources.DeliveyItemsResources;
import com.devcrawlers.simply.shopping.resources.DummyPaymentRequestResource;
import com.devcrawlers.simply.shopping.resources.ItemAddResource;
import com.devcrawlers.simply.shopping.resources.ItemUpdateResource;
import com.devcrawlers.simply.shopping.resources.MessageResponseResource;
import com.devcrawlers.simply.shopping.resources.OrderAddResource;
import com.devcrawlers.simply.shopping.resources.OrderItemAddResource;
import com.devcrawlers.simply.shopping.resources.OrderItemUpdateResource;
import com.devcrawlers.simply.shopping.resources.OrderUpdateResource;
import com.devcrawlers.simply.shopping.resources.SellerAddResource;
import com.devcrawlers.simply.shopping.resources.SellerUpdateResource;
import com.devcrawlers.simply.shopping.resources.ValidateResource;


/**
 * 
 * This will return the relevant object based on the caught exception
 * 
 * @author menukaj
 * 
 */
@RestControllerAdvice
public class BaseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment environment;

	@Override 
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		messageResponseResource.setMessage(environment.getProperty("common.invalid-url-pattern"));
		messageResponseResource.setDetails(ex.getMessage());
		return new ResponseEntity<>(messageResponseResource, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({NoRecordFoundException.class})
	public ResponseEntity<Object> noRecordFoundException(NoRecordFoundException ex, WebRequest request) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		messageResponseResource.setMessage(ex.getMessage());
		return new ResponseEntity<>(messageResponseResource, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({ValidateRecordException.class})
	public ResponseEntity<Object> validateRecordException(ValidateRecordException ex, WebRequest request) {
		try {
		ValidateResource typeValidation = new ValidateResource();
		Class validationDetailClass = typeValidation.getClass();
		Field sField = validationDetailClass.getDeclaredField(ex.getField());
		sField.setAccessible(true);
		sField.set(typeValidation, ex.getMessage());
		return new ResponseEntity<>(typeValidation, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e) {
			MessageResponseResource messageResponseResource = new MessageResponseResource();
			messageResponseResource.setMessage(environment.getProperty("common.internal-server-error"));
			messageResponseResource.setDetails(e.getMessage());
			return new ResponseEntity<>(messageResponseResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override 
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
		
		try {
			 Field sField=null;
			 String fieldName=null;
			 Integer index=null;
			 Integer atPoint=null;
			 Integer index1=null;
			 Integer atPoint1=null;
			String className=ex.getBindingResult().getObjectName();
			switch(className){ 
        		
        	case "commonAddResource": 
        		CommonRequestResource  commonAddResource = new CommonRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  commonAddResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(commonAddResource.getClass().cast(commonAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(commonAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "attributeValueAddResource": 
        		AttributeValueRequestResource  attributeValueAddResource = new AttributeValueRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  attributeValueAddResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(attributeValueAddResource.getClass().cast(attributeValueAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(attributeValueAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "itemAddResource": 
        		ItemAddResource  itemAddResource = new ItemAddResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  itemAddResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(itemAddResource.getClass().cast(itemAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(itemAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "itemUpdateResource": 
        		ItemUpdateResource  itemUpdateResource = new ItemUpdateResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  itemUpdateResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(itemUpdateResource.getClass().cast(itemUpdateResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(itemUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);	
        	case "dummyPaymentRequestResource": 
        		DummyPaymentRequestResource  dummyPaymentRequestResource = new DummyPaymentRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  dummyPaymentRequestResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(dummyPaymentRequestResource.getClass().cast(dummyPaymentRequestResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(dummyPaymentRequestResource, HttpStatus.UNPROCESSABLE_ENTITY);
				
        	case "deliveyInfoRequestResource": 
        		DeliveyInfoRequestResource deliveyInfoRequestResource = new DeliveyInfoRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					fieldName=error.getField();
					if(fieldName.startsWith("items")) {
						 fieldName=fieldName.replace("items", "");
							 atPoint = fieldName.indexOf(']');
							 index=Integer.parseInt(fieldName.substring(1, atPoint));
							 fieldName=fieldName.substring(atPoint+2);
							 for (int i=0; i<=index; i++) {
								 if(deliveyInfoRequestResource.getItems()==null || deliveyInfoRequestResource.getItems().isEmpty()) {
									 deliveyInfoRequestResource.setItems(new ArrayList<DeliveyItemsResources>());
									 deliveyInfoRequestResource.getItems().add(i, new DeliveyItemsResources());
								 }else{
									 if((deliveyInfoRequestResource.getItems().size()-1)<i) {
										 deliveyInfoRequestResource.getItems().add(i, new DeliveyItemsResources());
									 }
								 }
							 }
							 sField=deliveyInfoRequestResource.getItems().get(index).getClass().getDeclaredField(fieldName);
							 sField.setAccessible(true);
							 sField.set(deliveyInfoRequestResource.getItems().get(index), error.getDefaultMessage());
				}else {
						sField =  deliveyInfoRequestResource.getClass().getDeclaredField(error.getField());
		                sField.setAccessible(true);
		                sField.set(deliveyInfoRequestResource.getClass().cast(deliveyInfoRequestResource), error.getDefaultMessage());
					}
				}
				return new ResponseEntity<>(deliveyInfoRequestResource, HttpStatus.UNPROCESSABLE_ENTITY);				
        	case "orderAddResource": 
        		OrderAddResource orderAddResource = new OrderAddResource();
                for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                    fieldName=error.getField();
                    if(fieldName.startsWith("itemList")) {
                         fieldName=fieldName.replace("itemList", "");
                             atPoint = fieldName.indexOf(']');
                             index=Integer.parseInt(fieldName.substring(1, atPoint));
                             fieldName=fieldName.substring(atPoint+2);
                             for (int i=0; i<=index; i++) {
                                 if(orderAddResource.getItemList()==null || orderAddResource.getItemList().isEmpty()) {
                                	 orderAddResource.setItemList(new ArrayList<OrderItemAddResource>());
                                	 orderAddResource.getItemList().add(i, new OrderItemAddResource());
                                 }else{
                                     if((orderAddResource.getItemList().size()-1)<i) {
                                    	 orderAddResource.getItemList().add(i, new OrderItemAddResource());
                                     }
                                 }
                             }
                             sField=orderAddResource.getItemList().get(index).getClass().getDeclaredField(fieldName);
                             sField.setAccessible(true);
                             sField.set(orderAddResource.getItemList().get(index), error.getDefaultMessage());
                    }else {
                        sField =  orderAddResource.getClass().getDeclaredField(error.getField());
                        sField.setAccessible(true);
                        sField.set(orderAddResource.getClass().cast(orderAddResource), error.getDefaultMessage());
                    }
                }
                return new ResponseEntity<>(orderAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "orderUpdateResource": 
        		OrderUpdateResource orderUpdateResource = new OrderUpdateResource();
                for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                    fieldName=error.getField();
                    if(fieldName.startsWith("itemList")) {
                         fieldName=fieldName.replace("itemList", "");
                             atPoint = fieldName.indexOf(']');
                             index=Integer.parseInt(fieldName.substring(1, atPoint));
                             fieldName=fieldName.substring(atPoint+2);
                             for (int i=0; i<=index; i++) {
                                 if(orderUpdateResource.getItemList()==null || orderUpdateResource.getItemList().isEmpty()) {
                                	 orderUpdateResource.setItemList(new ArrayList<OrderItemUpdateResource>());
                                	 orderUpdateResource.getItemList().add(i, new OrderItemUpdateResource());
                                 }else{
                                     if((orderUpdateResource.getItemList().size()-1)<i) {
                                    	 orderUpdateResource.getItemList().add(i, new OrderItemUpdateResource());
                                     }
                                 }
                             }
                             sField=orderUpdateResource.getItemList().get(index).getClass().getDeclaredField(fieldName);
                             sField.setAccessible(true);
                             sField.set(orderUpdateResource.getItemList().get(index), error.getDefaultMessage());
                    }else {
                        sField =  orderUpdateResource.getClass().getDeclaredField(error.getField());
                        sField.setAccessible(true);
                        sField.set(orderUpdateResource.getClass().cast(orderUpdateResource), error.getDefaultMessage());
                    }
                }
                return new ResponseEntity<>(orderUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "sellerAddResource": 
        		SellerAddResource  sellerAddResource = new SellerAddResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  sellerAddResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(sellerAddResource.getClass().cast(sellerAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(sellerAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
        	case "sellerUpdateResource": 
        		SellerUpdateResource  sellerUpdateResource = new SellerUpdateResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField =  sellerUpdateResource.getClass().getDeclaredField(error.getField());
		            sField.setAccessible(true);
		            sField.set(sellerUpdateResource.getClass().cast(sellerUpdateResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(sellerUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
	        	default:   
	        		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	        }
			
		} catch (Exception e) {
			MessageResponseResource messageResponseResource = new MessageResponseResource();
			messageResponseResource.setMessage(environment.getProperty("common.internal-server-error"));
			messageResponseResource.setDetails(e.getMessage());
			return new ResponseEntity<>(messageResponseResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }				

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> exception(Exception ex, WebRequest request) {
		MessageResponseResource messageResponseResource = new MessageResponseResource();
		messageResponseResource.setMessage(ex.getMessage());
		return new ResponseEntity<>(messageResponseResource, HttpStatus.UNPROCESSABLE_ENTITY);

	}
	
	@ExceptionHandler({InvalidDetailListServiceIdException.class})
	public ResponseEntity<Object> invalidDetailListServiceIdException(InvalidDetailListServiceIdException ex, WebRequest request) {
		if(ex.getActionType().equals(ActionType.ORDER_ITEM_SAVE)) {
			OrderAddResource orderAddResource = validateOrderAddResource(ex);
			return new ResponseEntity<>(orderAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}else if(ex.getActionType().equals(ActionType.ORDER_ITEM_UPDATE)) {
			OrderUpdateResource orderUpdateResource = validateOrderUpdateResource(ex);
			return new ResponseEntity<>(orderUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
		}else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private OrderAddResource validateOrderAddResource(InvalidDetailListServiceIdException ex) {
		OrderAddResource orderAddResources = new OrderAddResource();
		List<OrderItemAddResource> orderItemAddResource=new ArrayList<>();
		Integer index=ex.getIndex();
		for(int i=0;i<=ex.getIndex();i++){  
			orderItemAddResource.add(i, new OrderItemAddResource());
		}
		switch(ex.getServiceEntity()) 
        {
	        case ITEM_ID:
	        	orderItemAddResource.get(index).setItemsId(ex.getMessage());
	            break;    
            default: 
            	
        }
		orderAddResources.setItemList(orderItemAddResource);
		return orderAddResources;
	}
	
	private OrderUpdateResource validateOrderUpdateResource(InvalidDetailListServiceIdException ex) {
		OrderUpdateResource orderUpdateResources = new OrderUpdateResource();
		List<OrderItemUpdateResource> orderItemUpdateResource=new ArrayList<>();
		Integer index=ex.getIndex();
		for(int i=0;i<=ex.getIndex();i++){  
			orderItemUpdateResource.add(i, new OrderItemUpdateResource());
		}
		switch(ex.getServiceEntity()) 
        {
	        case ID:
	        	orderItemUpdateResource.get(index).setId(ex.getMessage());
	            break;
        	case ITEM_ID:
        		orderItemUpdateResource.get(index).setItemsId(ex.getMessage());
	            break;    
            default: 
            	
        }
		orderUpdateResources.setItemList(orderItemUpdateResource);
		return orderUpdateResources;
	}

}
