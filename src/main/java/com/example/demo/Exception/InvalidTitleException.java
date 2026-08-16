package com.example.demo.Exception;

public class InvalidTitleException extends RuntimeException{
	public InvalidTitleException(String message){
		super(message);
	}
}
