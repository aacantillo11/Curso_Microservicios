package com.alejo.alejocliente2.config;

import feign.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.LoadBalancerFeignRequestTransformer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;


@Configuration
public class LoadBalancerConfigurationClient {

    private static final Logger log = LoggerFactory.getLogger(LoadBalancerConfigurationClient.class);
    private static String INSTANCE_ID="";
    private static String URL="";


    /*@Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context){
        log.info("Configuring load balancer to prefer same instance");
        return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient().withSameInstancePreference().build(context);
    }

    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }

    @Bean
    public LoadBalancerFeignRequestTransformer transformer() {
        return new LoadBalancerFeignRequestTransformer() {

            @Override
            public Request transformRequest(Request request, ServiceInstance instance) {
                Map<String, Collection<String>> headers = new HashMap<>(request.headers());
                log.info("Instancia: {} y host: {}",instance.getInstanceId(),instance.getHost());
                headers.put("X-ServiceId", Collections.singletonList(instance.getServiceId()));
                headers.put("X-InstanceId", Collections.singletonList(instance.getInstanceId()));
                log.info("Headers: {}",headers);

                return Request.create(request.httpMethod(), request.url(), headers, request.body(), request.charset(),
                        request.requestTemplate());
            }
        };
    }*/

    @Bean
    public LoadBalancerFeignRequestTransformer transformer() {
        return new LoadBalancerFeignRequestTransformer() {

            @Override
            public Request transformRequest(Request request, ServiceInstance instance) {

                Map<String, Collection<String>> headers = new HashMap<>(request.headers());
                //log.info("Instancia: {} y host: {}",instance.getInstanceId(),instance.getHost());
                headers.put("X-ServiceId", Collections.singletonList(instance.getServiceId()));
                INSTANCE_ID = INSTANCE_ID.isBlank() ? instance.getInstanceId() : INSTANCE_ID;
                URL = URL.isBlank() ? request.url() : URL;
                System.out.println("INSTANCE_ID = " + INSTANCE_ID + "  Service " + instance.getServiceId());
                headers.put("X-InstanceId", Collections.singletonList(INSTANCE_ID));
                //log.info("Headers: {}",headers);
                log.info("URL: {}", request.url());
                Request requestFinal = Request.create(request.httpMethod(), URL, headers, request.body(), request.charset(),
                        request.requestTemplate());

                log.info("Request: {}",requestFinal);
                return requestFinal;
            }
        };
    }


}
