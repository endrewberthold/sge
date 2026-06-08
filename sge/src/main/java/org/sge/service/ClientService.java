package org.sge.service;

import org.sge.dtos.ClientDetailsResponseDTO;
import org.sge.dtos.ClientRequestDTO;
import org.sge.dtos.ClientResponseDTO;
import org.sge.dtos.VehicleResponseDTO;
import org.sge.entity.Client;
import org.sge.entity.User;
import org.sge.repository.ClientRepository;
import org.sge.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(
            ClientRepository clientRepository, UserRepository userRepository
    ){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    /**
     * Método privado para reutilização em blocos onde
     * é pertinente puxar informações do cliente e seus
     * respectivos ativos (Veiculos, dados cadastrais, etc)
     * */
    private ClientDetailsResponseDTO toDetailsDTO(
            Client client
    ){
        List<VehicleResponseDTO> vehicles =
                client.getVehicles()
                        .stream()
                        .map(vehicle -> new VehicleResponseDTO(
                                vehicle.getId(),
                                vehicle.getMark(),
                                vehicle.getModel(),
                                vehicle.getColor()
                        ))
                        .toList();

        return new ClientDetailsResponseDTO(
                client.getId(),
                client.getName(),
                client.getDocument(),
                client.getPhone(),
                vehicles
        );
    }

    public ClientResponseDTO create(ClientRequestDTO dto){

        Optional<Client> existingClient = clientRepository.findByDocument(dto.document());

        if(existingClient.isPresent()){
            throw new RuntimeException("Cliente já cadastrado.");
        }

        Client client = new Client();

        client.setName(dto.name());
        client.setDocument(dto.document());
        client.setPhone(dto.phone());

        Client savedClient = clientRepository.save(client);

        return new ClientResponseDTO(
                savedClient.getId(),
                savedClient.getName()
        );
    }

    public ClientDetailsResponseDTO me(){

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        return toDetailsDTO(
                user.getClient()
        );
    }

    /**
     * Endpoint somente para ADMINISTRADORES*/
    public ClientDetailsResponseDTO findById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));

        return toDetailsDTO(client);
    }

    /**
     * Endpoint somente para ADMINISTRADORES*/
    public List<ClientResponseDTO> findAll(){
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientResponseDTO(
                        client.getId(),
                        client.getName()
                ))
                .toList();
    }
}
