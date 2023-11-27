package com.apirest.service;

import com.apirest.model.Client;
import com.apirest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class ClientService {

    private  final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public Client getById(Integer clientId) {
        System.out.println("> Get client by id " + clientId.toString());
        Optional<Client> optionalClient = this.clientRepository.findById(clientId);
        return optionalClient.orElse(null);
    }

    public Integer calculateAgeFromBirthDate(Date date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
