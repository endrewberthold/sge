package org.sge.repository;

import org.sge.entity.ParkingSession;
import org.sge.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    boolean existsByVehicleAndExitTimeIsNull(Vehicle vehicle);

    Optional<ParkingSession> findByVehicleAndExitTimeIsNull(Vehicle vehicle);
}
