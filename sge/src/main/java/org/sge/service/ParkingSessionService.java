package org.sge.service;

import org.sge.dtos.ParkingEntryDTO;
import org.sge.dtos.ParkingExitDTO;
import org.sge.dtos.ParkingSessionResponseDTO;
import org.sge.entity.ParkingRate;
import org.sge.entity.ParkingSession;
import org.sge.entity.Vehicle;
import org.sge.enums.RateType;
import org.sge.enums.SessionStatus;
import org.sge.repository.ParkingRateRepository;
import org.sge.repository.ParkingSessionRepository;
import org.sge.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingSessionService {

    private final VehicleRepository vehicleRepository;
    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingRateRepository parkingRateRepository;

    public ParkingSessionService(
            VehicleRepository vehicleRepository,
            ParkingSessionRepository parkingSessionRepository, ParkingRateRepository parkingRateRepository
    ){
        this.vehicleRepository = vehicleRepository;
        this.parkingSessionRepository = parkingSessionRepository;
        this.parkingRateRepository = parkingRateRepository;
    }

    private BigDecimal calculateAmount(
            Duration duration,
            ParkingRate rate
    ){
        return switch (rate.getType()){
            case HOUR -> rate.getAmount().multiply(
                    BigDecimal.valueOf(Math.ceil(duration.toHours() / 60.0))
            );
            case DAY -> rate.getAmount().multiply(
                    BigDecimal.valueOf(
                            Math.ceil(duration.toHours() / 24.0))
            );
            case WEEK -> rate.getAmount().multiply(
                    BigDecimal.valueOf(Math.ceil(duration.toHours() / 7.0))
            );
            case MONTH -> rate.getAmount().multiply(
                    BigDecimal.valueOf(Math.ceil(duration.toHours() / 30.0))
            );
        };
    }

    public ParkingSessionResponseDTO registerEntry(ParkingEntryDTO dto) {

        Vehicle vehicle = vehicleRepository.findByPlate(dto.plate())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Optional<ParkingSession> openSession = parkingSessionRepository
                        .findByVehicleAndStatus(
                                vehicle,
                                SessionStatus.OPEN
                        );

        if (openSession.isPresent()){
            throw new RuntimeException("Veículo já está estacionado.");
        }

        ParkingSession session = new ParkingSession();

        session.setVehicle(vehicle);
        session.setEntryTime(LocalDateTime.now());

        session.setStatus(
                SessionStatus.OPEN
        );

        ParkingSession saved = parkingSessionRepository
                        .save(session);

        return new ParkingSessionResponseDTO(
                saved.getId(),
                saved.getVehicle().getPlate(),
                saved.getEntryTime(),
                saved.getExitTime(),
                saved.getStatus(),
                saved.getTotalAmount()
        );
        }


    public ParkingSessionResponseDTO registerExit(ParkingExitDTO dto){

        Vehicle vehicle = vehicleRepository.findByPlate(dto.plate())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        ParkingSession session = parkingSessionRepository
                        .findByVehicleAndStatus(
                                vehicle,
                                SessionStatus.OPEN
                        )
                        .orElseThrow(() -> new RuntimeException("Nenhuma sessão aberta"));

        session.setExitTime(LocalDateTime.now());

        Duration duration = Duration.between(
                        session.getEntryTime(),
                        session.getExitTime()
                );

        ParkingRate rate = parkingRateRepository
                        .findByTypeAndActiveTrue(
                                RateType.HOUR
                        )
                        .orElseThrow(() -> new RuntimeException("Tarifa horária não encontrada."));

        session.setTotalAmount(calculateAmount(duration, rate));

        session.setStatus(SessionStatus.CLOSED);

        ParkingSession saved = parkingSessionRepository
                        .save(session);

        return new ParkingSessionResponseDTO(
                saved.getId(),
                saved.getVehicle().getPlate(),
                saved.getEntryTime(),
                saved.getExitTime(),
                saved.getStatus(),
                saved.getTotalAmount()
        );
    }
}
