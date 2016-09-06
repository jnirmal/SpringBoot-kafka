package com.james.kafkarproject.service.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.james.kafkarproject.service.KafkaProducer;

@RestController
public class IndexController {

	@Autowired
	KafkaProducer kafkaProducer;
	
	
    @RequestMapping("/sendMessage")
    public String display() {
    	kafkaProducer.send("Welcome to Kafka "+new Date());
       return "Message Sent Successfully";
    }

}
