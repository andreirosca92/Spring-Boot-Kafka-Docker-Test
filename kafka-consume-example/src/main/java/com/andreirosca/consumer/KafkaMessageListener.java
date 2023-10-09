package com.andreirosca.consumer;

import com.andreirosca.dto.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaMessageListener{

    Logger LOGGER = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "andrei", groupId = "andrei-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume1(@Payload Customer customer) throws Exception {
        try{

            LOGGER.info("consumer1 consume the events {}" ,customer.toString());
        }catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }

    }
    /*
    @KafkaListener(topics = "andrei-demo", groupId = "andrei-group")
    public void consume2(String message){
        LOGGER.info("consumer2 consume the message {}", message);
    }
    @KafkaListener(topics = "andrei-demo", groupId = "andrei-group")
    public void consume3(String message){
        LOGGER.info("consumer3 consume the message {}", message);
    }
    @KafkaListener(topics = "andrei-demo", groupId = "andrei-group")
    public void consume4(String message){
        LOGGER.info("consumer4 consume the message {}", message);
    }
*/
}
