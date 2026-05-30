package org.sge.controllers;

import org.sge.entity.ParkingSession;
import org.sge.service.ParkingSessionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingSessionController {

    private final ParkingSessionService parkingSessionService;

    public ParkingSessionController(ParkingSessionService parkingSessionService){
        this.parkingSessionService = parkingSessionService;
    }

    @PostMapping("/entry/{plate}")
    public ParkingSession registerEntry(@PathVariable String plate){
        return parkingSessionService.registerEntry(plate);
    }

    @PostMapping("/exit/{plate}")
    public ParkingSession registerExit(@PathVariable String plate){
        return parkingSessionService.registerExit(plate);
    }
}
