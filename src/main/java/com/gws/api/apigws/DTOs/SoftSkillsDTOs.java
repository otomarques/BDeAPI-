package com.gws.api.apigws.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SoftSkillsDTOs(
        @NotBlank String nome,
        UUID usuarios
) {

}
