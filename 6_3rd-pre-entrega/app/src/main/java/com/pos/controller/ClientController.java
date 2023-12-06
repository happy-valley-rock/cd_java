package com.pos.controller;

import com.pos.model.Client;
import com.pos.model.MessageResponse;
import com.pos.model.dto.ClientDtoRequest;
import com.pos.service.ClientService;
import com.pos.util.DtoEntityConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Create a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "")
    })
    @PostMapping("")
    public ResponseEntity<Client> postClient(@RequestBody ClientDtoRequest body) {
        Client client = this.dtoEntityConverter.convertClientToIdentity(body);
        client = this.clientService.createClient(client);
        return new ResponseEntity<Client>(client, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a client")
    @PutMapping("/{id}")
    public ResponseEntity<Client> putClient(@PathVariable Integer id, @RequestBody ClientDtoRequest body) {
        Client client = this.dtoEntityConverter.convertClientToIdentity(body);
        client.setId(id);
        client = this.clientService.updateClientById(client, id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @Operation(summary = "Read a client")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        Client client = this.clientService.getById(id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @Operation(summary = "Remove a client")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteClient(@PathVariable Integer id) {
        this.clientService.removeClient(id);
        return new ResponseEntity<MessageResponse>(new MessageResponse("Client deleted succesfully"), HttpStatus.OK);
    }

    @Operation(summary = "List of clients")
    @GetMapping("/list")
    public ResponseEntity<List> getClientList() {
        List<Client> clientList = this.clientService.getList();
        return new ResponseEntity<List>(clientList, HttpStatus.OK);
    }
}
