package com.gws.api.apigws.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_softskills")
public class SoftSkillsModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_softskill", nullable = false)
    private UUID id_softskills;

    @Column(nullable = false,unique = true)
    private String nome;

    @ManyToMany(mappedBy = "id_softskill")
    private Set<UsuarioModel> id_usuario = new HashSet<>();
}
