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
@Table(name = "tb_clientes")
public class ClientesModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente", nullable = false)
    private UUID id_clientes;

    @Column(name = "nome_empresa")
    private String nome_empresa;
    @Column(name = "nome_cliente")
    private String nome_cliente;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @Column(name = "url_img")
    private String url_img;




}
