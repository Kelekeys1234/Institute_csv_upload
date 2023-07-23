package com.example.demo;


import lombok.Data;

@Data
public class GenericWrapperDto<T> {
	private String message;
	private T data;
	private String status;
}
