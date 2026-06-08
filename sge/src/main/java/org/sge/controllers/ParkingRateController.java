package org.sge.controllers;

import org.sge.dtos.ParkingRateRequestDTO;
import org.sge.dtos.ParkingRateResponseDTO;
import org.sge.service.ParkingRateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-rates")
public class ParkingRateController {

    private final ParkingRateService parkingRateService;

    public ParkingRateController(ParkingRateService parkingRateService) {
        this.parkingRateService = parkingRateService;
    }

    @PostMapping
    public ParkingRateResponseDTO create(
            @RequestBody ParkingRateRequestDTO dto
    ){
        return parkingRateService.create(dto);
    }
}
