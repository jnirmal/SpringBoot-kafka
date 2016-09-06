package com.james.kafkarproject.service;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.messaging.MessageHandler;

@Configuration

public class KafkaProducer {
	
	@Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.messageKey}")
    private String messageKey;

    @Value("${kafka.broker.address}")
    private String brokerAddress;
    
    @Autowired
    private KafkaTemplate<String, String> template;

    public void send(String messageToSend) {
        	template.send(topic,messageToSend);
    }
    
   

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

    
	@Bean
    public ProducerFactory<String, String> producerFactory() {
		DefaultKafkaProducerFactory defaultKafkaProducerFactory = null;
		System.out.println("Inside producerFactory "+this.brokerAddress);
		try
		{
	        Map<String, Object> props = new HashMap();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
	        props.put(ProducerConfig.RETRIES_CONFIG, 0);
	        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
	        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        defaultKafkaProducerFactory = new DefaultKafkaProducerFactory(props);
     	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        return defaultKafkaProducerFactory ;
    }

}
