package org.sge.dtos;

import org.sge.enums.Role;

public record UserResponseDTO(
        Long id,
        String email,
        Role role
) {
}
