package com.pos.controller;

import com.pos.model.Client;
import com.pos.model.MessageResponse;
import com.pos.model.dto.ClientDtoRequest;
import com.pos.service.ClientService;
import com.pos.util.DtoEntityConverter;
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
    public ResponseEntity<Client> postClient(@RequestBody ClientDtoRequest body) {
        Client client = this.dtoEntityConverter.convertClientToIdentity(body);
        client = this.clientService.createClient(client);
        return new ResponseEntity<Client>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> putClient(@PathVariable Integer id, @RequestBody ClientDtoRequest body) {
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
        return new ResponseEntity<MessageResponse>(new MessageResponse("Client deleted succesfully"), HttpStatus.OK);
    }
}
