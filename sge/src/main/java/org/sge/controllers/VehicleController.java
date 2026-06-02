package org.sge.controllers;

import org.sge.dtos.VehicleRequestDTO;
import org.sge.dtos.VehicleResponseDTO;
import org.sge.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/{clientId}")
    public VehicleResponseDTO create(
            @PathVariable Long clientId,
            @RequestBody VehicleRequestDTO dto){

        return vehicleService.create(clientId, dto);
    }
}
