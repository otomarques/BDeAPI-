package com.gws.api.apigws.repositories;

import com.gws.api.apigws.models.DemandasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface DemandasRepository extends JpaRepository<DemandasModel, UUID> {
    DemandasModel findByTitulo(String titulo);
}
