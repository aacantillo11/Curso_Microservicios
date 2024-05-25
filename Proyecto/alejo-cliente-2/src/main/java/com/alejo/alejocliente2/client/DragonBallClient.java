package com.alejo.alejocliente2.client;

import com.alejo.alejocliente2.config.LoadBalancerConfigurationClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@LoadBalancerClient(name = "alejo-dragon-ball", configuration = RandomLoadBalancer.class)
@FeignClient(name = "alejo-dragon-ball")
public interface DragonBallClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/dragonball/greetings")
    public ResponseEntity<String> getDragonBallName();
}
