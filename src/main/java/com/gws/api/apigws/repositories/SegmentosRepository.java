package com.gws.api.apigws.repositories;

import com.gws.api.apigws.models.SegmentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SegmentosRepository extends JpaRepository<SegmentosModel, UUID> {
    SegmentosModel findBySegmento(String nome);
}
