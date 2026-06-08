package org.sge.dtos;

import java.util.List;

public record ClientDetailsResponseDTO(
        Long id,
        String name,
        String document,
        String phone,
        List<VehicleResponseDTO> vehicles
) {
}
