package org.sge.dtos;

public record LoginRequestDTO(
        String email,
        String password
) {
}
