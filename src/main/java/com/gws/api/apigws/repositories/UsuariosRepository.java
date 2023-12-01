package com.gws.api.apigws.repositories;

import com.gws.api.apigws.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioModel, UUID> {
    UserDetails findByEmail(String email);
}
