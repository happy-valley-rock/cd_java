package com.billing.service;

import com.billing.model.Client;
import com.billing.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getById(Integer clientId) {
        System.out.println("> Get client by id " + clientId.toString());
        Optional<Client> optionalClient = this.clientRepository.findById(clientId);
        return optionalClient.orElse(null);
    }

    public void createClient(Client client) {
        System.out.println("> Create a client");
        this.clientRepository.save(client);
    }

    public void updateClientById(Client client, Integer clientId) {
        System.out.println("> Update client with id " + clientId.toString());

        Client clientFounded = this.getById(clientId);
        if (clientFounded == null) throw new Error("Client does not exist");

        clientFounded = client;
        this.clientRepository.save(clientFounded);
    }
}
