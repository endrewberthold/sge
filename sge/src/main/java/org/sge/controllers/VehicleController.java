package org.sge.controllers;

import org.sge.entity.Vehicle;
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
    public Vehicle create(
            @PathVariable Long clientId,
            @RequestBody Vehicle vehicle){

        System.out.println(vehicle);

        return vehicleService.create(clientId, vehicle);
    }
}
