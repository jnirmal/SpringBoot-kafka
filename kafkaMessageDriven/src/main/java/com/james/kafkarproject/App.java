package com.james.kafkarproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableIntegration
@EnableScheduling
@ComponentScan(value={"com.james.kafkarproject.service.controller,com.james.kafkarproject.service"})
@EnableKafka
public class App 
{
    public static void main( String[] args )
    {
    	  ConfigurableApplicationContext configure = SpringApplication.run(App.class, args);
    
      }

      
   
}
