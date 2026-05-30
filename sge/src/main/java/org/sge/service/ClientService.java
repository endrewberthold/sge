package org.sge.service;

import org.sge.entity.Client;
import org.sge.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(
            ClientRepository clientRepository
    ){
        this.clientRepository = clientRepository;
    }

    public Client create(Client client){

        Optional<Client> existingClient = clientRepository.findByDocument(client.getDocument());

        if(existingClient.isPresent()){
            throw new RuntimeException("Cliente já cadastrado.");
        }

        return clientRepository.save(client);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }
}
