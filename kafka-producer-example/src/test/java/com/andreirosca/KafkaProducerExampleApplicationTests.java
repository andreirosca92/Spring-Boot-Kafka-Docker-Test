package com.andreirosca;

import com.andreirosca.dto.Customer;
import com.andreirosca.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerExampleApplicationTests {


	@Autowired
	KafkaMessagePublisher publisher;
	@Container
	static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));


	@DynamicPropertySource
	public static void initKafkaProperties(DynamicPropertyRegistry registry){
		registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

	}

	@Test
	public void testSendEventsToTopic(){
		publisher.sendEventsToTopic(new Customer(10, "Luca", "luca@esempio.com", "2384895949"));
		await().pollInterval(Duration.ofSeconds(5)).atMost(15, TimeUnit.SECONDS).untilAsserted(()->{
			// assert statement
		});
	}

}
