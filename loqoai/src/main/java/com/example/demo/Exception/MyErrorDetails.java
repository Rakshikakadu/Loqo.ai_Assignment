package com.example.demo.Exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyErrorDetails {
	
	private LocalDateTime timestamp;
	private String message;
	private String description;

}
