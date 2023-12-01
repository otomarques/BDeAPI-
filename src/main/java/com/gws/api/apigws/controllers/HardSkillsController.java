package com.gws.api.apigws.controllers;


import com.gws.api.apigws.DTOs.HardSkillsDTOs;
import com.gws.api.apigws.models.HardSkillsModel;
import com.gws.api.apigws.repositories.HardSkillsRepository;
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
@RequestMapping(value = "/hardskills")
public class HardSkillsController {
    @Autowired
    HardSkillsRepository hardSkillsRepository;

    @GetMapping
    public ResponseEntity<List<HardSkillsModel>> ListarHardSkills(){
        return ResponseEntity.status(HttpStatus.OK).body(hardSkillsRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> BuscarhardSkills(@PathVariable(value = "id") UUID id){
        Optional<HardSkillsModel> buscandohardSkills = hardSkillsRepository.findById(id);

        if (buscandohardSkills.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("HardSkill não encontrado");
        }


        return ResponseEntity.status(HttpStatus.OK).body(buscandohardSkills.get());
    }

    @PostMapping
    public ResponseEntity<Object> criarCliente(@RequestBody @Valid HardSkillsDTOs hardSkillsDTOs){
        if (hardSkillsRepository.findByNome(hardSkillsDTOs.nome()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HardSkills já cadastrado");
        }

        HardSkillsModel novaHardSkills = new HardSkillsModel();
        BeanUtils.copyProperties(hardSkillsDTOs, novaHardSkills);

        return ResponseEntity.status(HttpStatus.CREATED).body(hardSkillsRepository.save(novaHardSkills));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editarHardSkill(@PathVariable(value = "id") UUID id, @RequestBody @Valid HardSkillsDTOs hardSkillsDTOs){
        Optional<HardSkillsModel> buscandoHardskill = hardSkillsRepository.findById(id);

        if (buscandoHardskill.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hard Skill não encontrado");
        }

        HardSkillsModel hardSkillsEditada = new HardSkillsModel();
        BeanUtils.copyProperties(hardSkillsDTOs, hardSkillsEditada);


        return ResponseEntity.status(HttpStatus.CREATED).body(hardSkillsRepository.save(hardSkillsEditada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarHardSkill(@PathVariable(value = "id") UUID id){

        Optional<HardSkillsModel> hardSkillsBuscado = hardSkillsRepository.findById(id);

        if (hardSkillsBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hard Skill não encontrado");
        }

        hardSkillsRepository.delete(hardSkillsBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Hard Skill deletado com sucesso!");

    }
}
