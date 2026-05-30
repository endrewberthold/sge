package org.sge.dtos;

public record VehicleRequestDTO(
        String plate,
        String mark,
        String model,
        String color
) {
}
