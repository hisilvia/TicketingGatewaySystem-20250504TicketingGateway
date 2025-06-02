package com.syit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {

	//sender, recipient, message, subject
	private String recipientEmail;
	private String message;
	private String subject;
	
}
