package jotace.example.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProjectApplication.class, args);
    }

}
