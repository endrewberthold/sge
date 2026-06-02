package org.sge.controllers;

import org.sge.dtos.ClientRequestDTO;
import org.sge.dtos.ClientResponseDTO;
import org.sge.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ClientResponseDTO create(
            @RequestBody ClientRequestDTO dto
    ){
        return clientService.create(dto);
    }

    @GetMapping("/all")
    public List<ClientResponseDTO> findAll(){
        return clientService.findAll();
    }
}
