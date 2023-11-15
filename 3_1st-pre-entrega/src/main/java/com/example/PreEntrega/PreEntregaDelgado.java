package com.example.PreEntrega;

import com.example.PreEntrega.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PreEntregaDelgado {
    @Autowired
    ClientRepository clientRepository;

    //Añadir todas las capas de repository que faltan

    public static void main(String[] args) {
        SpringApplication.run(PreEntregaDelgado.class, args);
    }

}
