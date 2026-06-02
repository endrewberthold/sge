package org.sge.service;

import org.sge.dtos.ClientRequestDTO;
import org.sge.dtos.ClientResponseDTO;
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

    public ClientResponseDTO create(ClientRequestDTO dto){

        Optional<Client> existingClient = clientRepository.findByDocument(dto.document());

        if(existingClient.isPresent()){
            throw new RuntimeException("Cliente já cadastrado.");
        }

        Client client = new Client();

        client.setName(dto.name());
        client.setDocument(dto.document());
        //client.setEmail(dto.email());
        client.setPhone(dto.phone());

        Client savedClient = clientRepository.save(client);

        return new ClientResponseDTO(
                savedClient.getId(),
                savedClient.getName()
        //        savedClient.getEmail()
        );
    }

    public List<ClientResponseDTO> findAll(){
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientResponseDTO(
                        client.getId(),
                        client.getName()
                        //client.getEmail()
                ))
                .toList();
    }
}
