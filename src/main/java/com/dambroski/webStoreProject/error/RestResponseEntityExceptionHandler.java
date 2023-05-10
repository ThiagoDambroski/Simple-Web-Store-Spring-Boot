package com.dambroski.webStoreProject.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ErrorMessage> itemNotFoundException(ItemNotFoundException exception, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException exception, WebRequest request){
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorMessage> categoryNotFoundException(CategoryNotFoundException exception, 
			WebRequest request){
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(ItemDontHaveCategoryException.class)
	public ResponseEntity<ErrorMessage> itemDontHaveCategoryException(ItemDontHaveCategoryException exception,
			WebRequest request){
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	@ExceptionHandler(NotEnoughItemsException.class)
	public ResponseEntity<ErrorMessage> notEnoughtItemException(NotEnoughItemsException exception,
			WebRequest request){
		
		ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
		
	}
	
	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<ErrorMessage> orderItemNotFoundException(OrderItemNotFoundException exception,
			WebRequest request){
		
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorMessage> orderNotFoundException(OrderNotFoundException exception, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		
	}
	
	@ExceptionHandler(CanNotPaidCancelledOrderException.class)
	public ResponseEntity<ErrorMessage> canNotPaidCancelledOrder(CanNotPaidCancelledOrderException exception, 
			WebRequest request){
		
		ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
	}
}
