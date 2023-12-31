package com.pos.service;

import com.pos.exceptions.EntityNotFoundException;
import com.pos.model.Client;
import com.pos.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getById(Integer clientId) {
        System.out.println("> Get client by id " + clientId.toString());
        try {
            Optional<Client> optionalClient = this.clientRepository.findById(clientId);
            if (optionalClient.orElse(null) == null) throw new EntityNotFoundException("Client does not exist");
            return optionalClient.get();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Client createClient(Client client) {
        System.out.println("> Create a client");
        try {
            return this.clientRepository.save(client);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Client updateClientById(Client client, Integer clientId) {
        System.out.println("> Update client with id " + clientId.toString());
        try {
            Client clientFounded = this.getById(clientId);
            clientFounded = client;
            return this.clientRepository.save(clientFounded);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public void removeClient(Integer clientId) {
        System.out.println("> Remove client with id " + clientId.toString());
        try {
            this.getById(clientId);
            this.clientRepository.deleteById(clientId);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public List<Client> getList() {
        System.out.println("> Get client list");
        try {
            List<Client> clientList = this.clientRepository.findAll();
            return clientList;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
