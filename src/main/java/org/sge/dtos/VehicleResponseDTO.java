package org.sge.dtos;

public record VehicleResponseDTO(
        Long id,
        String mark,
        String model,
        String color
) {
}
