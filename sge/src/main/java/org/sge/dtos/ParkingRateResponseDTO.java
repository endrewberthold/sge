package org.sge.dtos;

import org.sge.enums.RateType;

import java.math.BigDecimal;

public record ParkingRateResponseDTO(
        Long id,
        RateType type,
        BigDecimal amount,
        Boolean active
) {
}
