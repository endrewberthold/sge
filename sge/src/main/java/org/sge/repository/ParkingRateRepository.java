package org.sge.repository;

import org.sge.entity.ParkingRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRateRepository extends JpaRepository<ParkingRate, Long> {
}
