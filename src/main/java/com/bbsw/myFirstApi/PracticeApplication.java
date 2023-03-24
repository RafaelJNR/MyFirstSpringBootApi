package com.bbsw.myFirstApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.bbsw"})
@EntityScan( value = {"com.bbsw"} ) //Scans all entities included from com.bbsw package
@EnableJpaRepositories("com.bbsw")
public class PracticeApplication {

    public static void main(String[] args) {

        SpringApplication.run(PracticeApplication.class, args);



    }

}
