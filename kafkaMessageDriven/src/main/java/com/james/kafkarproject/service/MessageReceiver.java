package com.james.kafkarproject.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	public MessageReceiver() {
		
	}
	
	@Resource  
	PollableChannel fromKafka;
	
	@Scheduled(fixedRate = 1000)
	private void receiveMsg()
	{
		Message<?> received = fromKafka.receive(10000);
		while (received != null) {
			System.out.println(received.getPayload());
			received = fromKafka.receive(10000);
		}
		
	}

}
