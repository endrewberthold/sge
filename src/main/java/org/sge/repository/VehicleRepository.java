package org.sge.repository;

import org.sge.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlate(String Plate);
}
