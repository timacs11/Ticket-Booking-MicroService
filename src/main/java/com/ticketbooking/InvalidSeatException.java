package com.ticketbooking;

public class InvalidSeatException extends RuntimeException {

	public InvalidSeatException(){
		super();
	}
	public InvalidSeatException(String message){
		super(message);
	}
}
