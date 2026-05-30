package org.sge.dtos;

public record VehicleResponseDTO(
        Long id,
        String plate,
        String mark,
        String model,
        String color
) {
}
