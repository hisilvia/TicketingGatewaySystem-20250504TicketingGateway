package com.syit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syit.component.EmailClient;
import com.syit.model.EmailRequest;

@RestController
@RequestMapping("/api/notification")
public class EmailSenderController {

	@Autowired
	EmailClient emailClient;
	
	@PostMapping
	public void emailSend(@RequestBody EmailRequest eq) {
		emailClient.emailSendClient(eq);
	}
}
