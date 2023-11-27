package com.apirest.controller;

import com.apirest.model.ClientResponse;
import com.apirest.model.Client;
import com.apirest.model.ErrorResponse;
import com.apirest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/{id}/age")
    public ResponseEntity<?> getAge(@PathVariable Integer id) {
        System.out.println("> Request age for a client...");

        if (id == null) {
            System.out.println("> Error: empty or invalid id client");
            ErrorResponse errorResponse = new com.apirest.model.ErrorResponse("Error, empty or invalid id client");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Client client = this.clientService.getById(id);

        if (client == null) {
            System.out.println("> Error: the client does not exist");
            ErrorResponse errorResponse = new com.apirest.model.ErrorResponse("Error, the client does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Date birthDate = client.getBirthDate();
        Integer age = this.clientService.calculateAgeFromBirthDate(birthDate);

        ClientResponse clientResponse = new ClientResponse(
                client.getFirstName(),
                client.getLastName(),
                age
        );

        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }
}
