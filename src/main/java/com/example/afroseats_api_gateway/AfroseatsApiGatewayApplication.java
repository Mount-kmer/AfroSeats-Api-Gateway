package com.example.afroseats_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AfroseatsApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfroseatsApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("account-service", r -> r.path("/users/**")
						.filters(f ->f.stripPrefix(1))
						.uri("lb://ACCOUNT-SERVICE"))
				.route("auth-service", r -> r.path("/authenticate/**")
						.uri("lb://AUTH-SERVICE"))
				.route("event-service", r -> r.path("/events/**")
						.uri("lb://EVENT-SERVICE"))
				.route("ticker-service", r -> r.path("/tickets/**")
						.uri("lb://TICKET-SERVICE"))
				.route("payment-service", r -> r.path("/checkout/**")
						.uri("lb://PAYMENT-SERVICE"))
				.build();
	}

}
