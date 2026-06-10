package org.sge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sge.enums.SessionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Vehicle vehicle;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;
}
