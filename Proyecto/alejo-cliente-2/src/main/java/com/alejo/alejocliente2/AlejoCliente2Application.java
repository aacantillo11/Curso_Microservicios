package com.alejo.alejocliente2;

import com.alejo.alejocliente2.client.DragonBallClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@EnableFeignClients
public class AlejoCliente2Application implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(AlejoCliente2Application.class);

	@Autowired
	private DragonBallClient dragonBallClient;

	public static void main(String[] args) {
		SpringApplication.run(AlejoCliente2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Dragon Ball Client");
		for (int i = 0; i < 4; i++) {
			ResponseEntity<String> response = dragonBallClient.getDragonBallName();
			log.info("Status Code: {} - Application Name: {}",response.getStatusCode(),response.getBody());
		}

	}
}
