package com.alejo.cliente.clients;

import com.alejo.cliente.config.AlejoLoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "alejo-dragon-ball")
@LoadBalancerClient(name = "alejo-dragon-ball", configuration = AlejoLoadBalancerConfiguration.class)
public interface DragonBallCharacterClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/dragonball/greetings")
    public ResponseEntity<String> getApplicationName();
}
