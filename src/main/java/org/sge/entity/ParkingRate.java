package org.sge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sge.enums.RateType;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking_rates")
public class ParkingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RateType type;

    private BigDecimal amount;

    private Boolean active;
}
