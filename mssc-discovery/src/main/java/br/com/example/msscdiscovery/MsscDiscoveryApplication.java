package br.com.example.msscdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsscDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscDiscoveryApplication.class, args);
    }

}