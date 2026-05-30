package org.sge.service;

import org.sge.entity.ParkingSession;
import org.sge.entity.Vehicle;
import org.sge.repository.ParkingSessionRepository;
import org.sge.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ParkingSessionService {

    private final VehicleRepository vehicleRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    public ParkingSessionService(
            VehicleRepository vehicleRepository,
            ParkingSessionRepository parkingSessionRepository
    ){
        this.vehicleRepository = vehicleRepository;
        this.parkingSessionRepository = parkingSessionRepository;
    }

    public ParkingSession registerEntry(String plate) {

        Vehicle vehicle = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        boolean hasOpenSession = parkingSessionRepository
                .existsByVehicleAndExitTimeIsNull(vehicle);

        if (hasOpenSession){
            throw new RuntimeException("Veículo já possui sessão aberta.");
        }

        ParkingSession session = new ParkingSession();

        session.setVehicle(vehicle);
        session.setEntryTime(LocalDateTime.now());

        return parkingSessionRepository.save(session);
        }

    public ParkingSession registerExit(String plate){

        Vehicle vehicle = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        ParkingSession session = parkingSessionRepository
                .findByVehicleAndExitTimeIsNull(vehicle)
                .orElseThrow(() -> new RuntimeException("Veículo não alocado"));

        session.setExitTime(LocalDateTime.now());

        long hours = Duration.between(
                session.getEntryTime(),
                session.getExitTime()
        ).toHours();

        BigDecimal totalAmount = BigDecimal.valueOf(hours * 10);

        session.setTotalAmount(totalAmount);

        return parkingSessionRepository.save(session);
    }
}
