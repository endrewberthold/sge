package org.sge.service;

import org.sge.dtos.VehicleRequestDTO;
import org.sge.dtos.VehicleResponseDTO;
import org.sge.entity.Client;
import org.sge.entity.Vehicle;
import org.sge.repository.ClientRepository;
import org.sge.repository.VehicleRepository;
import org.springframework.stereotype.Service;

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

    public VehicleResponseDTO create(Long clientId, VehicleRequestDTO dto){

        if (vehicleRepository.findByPlate(dto.plate()).isPresent()){
            throw new RuntimeException("Veículo já cadastrado.");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Vehicle vehicle = new Vehicle();

        vehicle.setPlate(dto.plate());
        vehicle.setMark(dto.mark());
        vehicle.setModel(dto.model());
        vehicle.setColor(dto.color());
        vehicle.setClient(client);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return new VehicleResponseDTO(
                savedVehicle.getId(),
                savedVehicle.getMark(),
                savedVehicle.getModel(),
                savedVehicle.getColor()
        );
    }
}
