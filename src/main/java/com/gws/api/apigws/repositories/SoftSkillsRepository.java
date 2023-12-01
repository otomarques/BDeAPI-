package com.gws.api.apigws.repositories;

import com.gws.api.apigws.models.SoftSkillsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SoftSkillsRepository extends JpaRepository<SoftSkillsModel, UUID> {
    SoftSkillsModel findByNome(String nome);
}
