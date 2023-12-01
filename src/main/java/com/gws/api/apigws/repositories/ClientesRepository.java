package com.gws.api.apigws.repositories;

import com.gws.api.apigws.models.ClientesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesModel, UUID> {
    ClientesModel findByEmail(String email);

}
