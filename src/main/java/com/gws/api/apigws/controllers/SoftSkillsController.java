package com.gws.api.apigws.controllers;

import com.gws.api.apigws.DTOs.SoftSkillsDTOs;
import com.gws.api.apigws.models.SoftSkillsModel;
import com.gws.api.apigws.repositories.SoftSkillsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/softskills")
public class SoftSkillsController {
    @Autowired
    SoftSkillsRepository softSkillsRepository;

    @GetMapping
    public ResponseEntity<List<SoftSkillsModel>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(softSkillsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarSoftSkills(@PathVariable(value = "id") UUID id){

        Optional<SoftSkillsModel> buscandoSoftSkills = softSkillsRepository.findById(id);

        if (buscandoSoftSkills.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SoftSkill não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(buscandoSoftSkills.get());
    }

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid SoftSkillsDTOs softSkillsDTOs){
        if (softSkillsRepository.findByNome(softSkillsDTOs.nome()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SoftSkill já cadastrado");
        }

        SoftSkillsModel novoUsuario = new SoftSkillsModel();
        BeanUtils.copyProperties(softSkillsDTOs, novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(softSkillsRepository.save(novoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editarSoftSkill(@PathVariable(value = "id") UUID id, @RequestBody @Valid SoftSkillsDTOs softSkillsDTOs){
        Optional<SoftSkillsModel> buscandoSoftSkill = softSkillsRepository.findById(id);

        if (buscandoSoftSkill.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Soft Skill não encontrado");
        }

        SoftSkillsModel softSkillsEditado = new SoftSkillsModel();
        BeanUtils.copyProperties(softSkillsDTOs, softSkillsEditado);


        return ResponseEntity.status(HttpStatus.CREATED).body(softSkillsRepository.save(softSkillsEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarSoftSkill(@PathVariable(value = "id") UUID id){

        Optional<SoftSkillsModel> softSkillsBuscado = softSkillsRepository.findById(id);

        if (softSkillsBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Soft Skill não encontrado");
        }

        softSkillsRepository.delete(softSkillsBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Soft Skill deletado com sucesso!");

    }
}
