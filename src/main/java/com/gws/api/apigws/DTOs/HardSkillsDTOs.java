package com.gws.api.apigws.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record HardSkillsDTOs(
        @NotBlank String nome,
        UUID usuarios
        ) {

}
