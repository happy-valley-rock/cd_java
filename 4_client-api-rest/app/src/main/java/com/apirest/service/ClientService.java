package com.apirest.service;

import com.apirest.model.Client;
import com.apirest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getById(Integer clientId) {
        System.out.println("> Get client by id " + clientId.toString());

        return this.clientRepository.getReferenceById(clientId);
    }

    public Integer calculateAgeFromBirthDate(Date date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

}
