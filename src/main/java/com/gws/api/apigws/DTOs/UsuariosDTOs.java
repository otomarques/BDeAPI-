package com.gws.api.apigws.DTOs;

import com.gws.api.apigws.models.TipoUsuarioModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

public record UsuariosDTOs(
        @NotBlank String nome,
        TipoUsuarioModel tipo_usuario,
        @NotBlank String sobrenome,
        @NotBlank String telefone,
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank String dataDaferias,
        @NotBlank String designacao,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String horasDaSemana,
        MultipartFile foto,
        String descricao,

        Set<UUID> id_demandas,
        Set<UUID> id_hardskills,
        Set<UUID> id_softSkills

        ) {

}
