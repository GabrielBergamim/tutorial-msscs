package br.com.example.msscauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsscAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscAuthApplication.class, args);
	}

}
