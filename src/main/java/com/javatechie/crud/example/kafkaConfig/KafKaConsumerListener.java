package com.javatechie.crud.example.kafkaConfig;


import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@Configuration
public class KafKaConsumerListener {

	@Autowired
	ProductService productService;
	@KafkaListener(topics="TOPIC_TEST",groupId="mygroup2",containerFactory = "productKafkaListenerFactory")
	public void consumerProduct(Product product) {
		System.out.println("Consumer Message  "+product);
		productService.saveProduct(product);

	}


}
