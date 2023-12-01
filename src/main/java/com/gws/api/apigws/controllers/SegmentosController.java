package com.gws.api.apigws.controllers;


import com.gws.api.apigws.DTOs.SegmentosDTOs;
import com.gws.api.apigws.models.SegmentosModel;
import com.gws.api.apigws.repositories.SegmentosRepository;
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
@RequestMapping(value = "/segmentos")
public class SegmentosController {
    @Autowired
    SegmentosRepository segmentosRepository;

    @GetMapping
    public ResponseEntity<List<SegmentosModel>> ListarSegmentos(){
        return ResponseEntity.status(HttpStatus.OK).body(segmentosRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> BuscarSegmentos(@PathVariable(value = "id") UUID id){
        Optional<SegmentosModel> buscandoSegmentos = segmentosRepository.findById(id);

        if (buscandoSegmentos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Segmento não encontrado");
        }


        return ResponseEntity.status(HttpStatus.OK).body(buscandoSegmentos.get());
    }

    @PostMapping
    public ResponseEntity<Object> criarSegmentos(@RequestBody @Valid SegmentosDTOs segmentosDTOs){
        if (segmentosRepository.findBySegmento(segmentosDTOs.segmento()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("hardSkills já cadastrado");
        }

        SegmentosModel novoSegmento = new SegmentosModel();
        BeanUtils.copyProperties(segmentosDTOs, novoSegmento);

        return ResponseEntity.status(HttpStatus.CREATED).body(segmentosRepository.save(novoSegmento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editarSegmentos(@PathVariable(value = "id") UUID id, @RequestBody @Valid SegmentosDTOs segmentosDTOs){
        Optional<SegmentosModel> buscandoSegmentos = segmentosRepository.findById(id);

        if (buscandoSegmentos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Segmentos não encontrado");
        }

        SegmentosModel segmentoEditado = new SegmentosModel();
        BeanUtils.copyProperties(segmentosDTOs, segmentoEditado);


        return ResponseEntity.status(HttpStatus.CREATED).body(segmentosRepository.save(segmentoEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarSegmento(@PathVariable(value = "id") UUID id){

        Optional<SegmentosModel> segmentoBuscado = segmentosRepository.findById(id);

        if (segmentoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        segmentosRepository.delete(segmentoBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");

    }
}
