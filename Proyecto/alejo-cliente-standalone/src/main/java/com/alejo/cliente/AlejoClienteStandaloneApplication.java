package com.alejo.cliente;

import com.alejo.cliente.clients.DragonBallCharacterClient;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AlejoClienteStandaloneApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(AlejoClienteStandaloneApplication.class);

	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private DragonBallCharacterClient dragonBallCharacterClient;

	public static void main(String[] args) {
		SpringApplication.run(AlejoClienteStandaloneApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//Application application = eurekaClient.getApplication("alejo-dragon-ball");
		//log.info("Application {}",application.getName());

		//List<InstanceInfo> instances = application.getInstances();
		//instances.forEach(i -> log.info("Instance info {}:{}",i.getIPAddr(),i.getPort()));

		log.info("Utilizando cliente Feing-------");
		for (int i = 0; i < 1; i++) {
			ResponseEntity<String> response = dragonBallCharacterClient.getApplicationName();
			log.info("ApplicationName " + Objects.requireNonNull(response.getBody()) + " Status Code:" + response.getStatusCode());
		}



	}
}
