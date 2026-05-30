package org.sge.service;

import org.sge.entity.Client;
import org.sge.entity.Vehicle;
import org.sge.repository.ClientRepository;
import org.sge.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;

    public VehicleService(
            VehicleRepository vehicleRepository,
            ClientRepository clientRepository
    ){
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
    }

    public Vehicle create(Long clientId, Vehicle vehicle){

        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(vehicle.getPlate());

        if(existingVehicle.isPresent()){
            throw new RuntimeException("Veículo já cadastrado.");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        vehicle.setClient(client);

        return vehicleRepository.save(vehicle);
    }
}
