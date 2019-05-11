package com.javasampleapproach.twitterbootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootTwitterBootstrapManualInstallApplication {

	public static void main(String[] args) {
		System.out.println("Application Started ");
		SpringApplication.run(SpringBootTwitterBootstrapManualInstallApplication.class, args);
		System.out.println("Application Started !!!");
	}
}