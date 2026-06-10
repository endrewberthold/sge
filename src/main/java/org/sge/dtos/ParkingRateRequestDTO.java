package org.sge.dtos;

import org.sge.enums.RateType;

import java.math.BigDecimal;

public record ParkingRateRequestDTO (
        RateType type,
        BigDecimal amount,
        Boolean active
){
}
