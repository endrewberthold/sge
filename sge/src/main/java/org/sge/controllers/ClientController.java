package org.sge.controllers;

import org.sge.entity.Client;
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
    public Client create(
            @RequestBody Client client
    ){
        return clientService.create(client);
    }

    @GetMapping("/all")
    public List<Client> findAll(){
        return clientService.findAll();
    }
}
