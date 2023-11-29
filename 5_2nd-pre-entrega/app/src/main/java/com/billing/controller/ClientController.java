package com.billing.controller;

import com.billing.model.Client;
import com.billing.model.MessageResponse;
import com.billing.model.dto.ClientDto;
import com.billing.service.ClientService;
import com.billing.util.DtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {

    private final ClientService clientService;

    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public ClientController(ClientService clientService, DtoEntityConverter dtoEntityConverter) {
        this.clientService = clientService;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    @PostMapping("")
    public ResponseEntity<Client> postClient(@RequestBody ClientDto body) {
        Client client = this.dtoEntityConverter.convertClientToIdentity(body);
        client = this.clientService.createClient(client);
        return new ResponseEntity<Client>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> putClient(@PathVariable Integer id, @RequestBody ClientDto body) {
        Client client = this.dtoEntityConverter.convertClientToIdentity(body);
        client.setId(id);
        client = this.clientService.updateClientById(client, id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        Client client = this.clientService.getById(id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteClient(@PathVariable Integer id) {
        this.clientService.removeClient(id);
        return new ResponseEntity<>(new MessageResponse("Client deleted succesfully"), HttpStatus.OK);
    }
}
