package org.sge.dtos;

import org.sge.enums.Role;

public record UserRequestDTO(
        String email,
        String password,
        Role role
) {
}
