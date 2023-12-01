package com.gws.api.apigws.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ClientesDTOs(
        @NotBlank String nome_empresa,
        @NotBlank String nome_cliente,
        @NotBlank String telefone,
        @NotBlank @Email String email,
        MultipartFile image

) {

}
