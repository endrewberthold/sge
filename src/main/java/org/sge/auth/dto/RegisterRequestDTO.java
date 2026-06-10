package org.sge.auth.dto;

import org.sge.enums.Role;

public record RegisterRequestDTO(
        String name,
        String document,
        String phone,
        String email,
        String password
) {
}
