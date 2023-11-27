package com.billingProject;

import com.billingProject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillingProject {
    @Autowired
    ClientRepository clientRepository;

    //Añadir todas las capas de repository que faltan

    public static void main(String[] args) {
        SpringApplication.run(BillingProject.class, args);
    }

}
