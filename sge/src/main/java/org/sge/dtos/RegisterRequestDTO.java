package org.sge.dtos;

public record RegisterRequestDTO(
        String name,
        String document,
        String phone,
        String email,
        String password
) {
}
