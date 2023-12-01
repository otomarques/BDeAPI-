package com.gws.api.apigws.models;

public enum TipoUsuarioModel {
    ADMIN("admin"),
    GESTOR("gestor"),
    COORDENADOR("coordenador"),
    CONSULTOR("consultor");

    private String tipo;

    TipoUsuarioModel(String tipoRecebido){
        this.tipo = tipoRecebido;
    }

    public String getRole(){
        return tipo;
    }
}
