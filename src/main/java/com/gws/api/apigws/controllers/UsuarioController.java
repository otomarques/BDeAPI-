package com.gws.api.apigws.controllers;

import com.gws.api.apigws.DTOs.UsuariosDTOs;
import com.gws.api.apigws.models.DemandasModel;
import com.gws.api.apigws.models.HardSkillsModel;
import com.gws.api.apigws.models.SoftSkillsModel;
import com.gws.api.apigws.models.UsuarioModel;
import com.gws.api.apigws.repositories.DemandasRepository;
import com.gws.api.apigws.repositories.HardSkillsRepository;
import com.gws.api.apigws.repositories.SoftSkillsRepository;
import com.gws.api.apigws.repositories.UsuariosRepository;
import com.gws.api.apigws.services.ConverterDataTime;
import com.gws.api.apigws.services.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    UsuariosRepository usuarioRepository;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ConverterDataTime converterDataTime;


    @Autowired
    DemandasRepository demandasRepository;
    @Autowired
    SoftSkillsRepository softSkillsRepository;
    @Autowired
    HardSkillsRepository hardSkillsRepository;



    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        Path urlImg = fileUploadService.getDiretorioImg();
        UsuarioModel usuarioimg = usuarioBuscado.get();
        UsuarioModel usuario = usuarioBuscado.get();
        usuario.setUrl_img(urlImg+ "\\" +usuarioimg.getUrl_img());

        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> criarUsuario(@ModelAttribute @Valid UsuariosDTOs usuariosDtos){
        if (usuarioRepository.findByEmail(usuariosDtos.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já cadastrado");
        }

        List<DemandasModel> demandasList = demandasRepository.findAllById(usuariosDtos.id_demandas());
        Set<DemandasModel> demandasAssociadas = new HashSet<>(demandasList);

        List<SoftSkillsModel> softSkillsList = softSkillsRepository.findAllById(usuariosDtos.id_softSkills());
        Set<SoftSkillsModel> softSkillsAssociadas = new HashSet<>(softSkillsList);

        List<HardSkillsModel> hardSkillsList = hardSkillsRepository.findAllById(usuariosDtos.id_hardskills());
        Set<HardSkillsModel> hardSkillsAssociadas = new HashSet<>(hardSkillsList);

        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuariosDtos, novoUsuario);

        String urlImagem;
        LocalDateTime dataAtual = LocalDateTime.now();

        try{
            urlImagem = fileUploadService.fazerUpload(usuariosDtos.foto());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        LocalTime horassemanais;
        LocalDate dataferias;

        try{
            horassemanais = converterDataTime.StringToDateTime(usuariosDtos.horasDaSemana());
            dataferias = converterDataTime.StringToDate(usuariosDtos.dataDaferias());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        novoUsuario.setUrl_img(urlImagem);
        novoUsuario.setDataCadastro(dataAtual);
        novoUsuario.setHoras_semanais(horassemanais);
        novoUsuario.setData_ferias(dataferias);



        if (demandasAssociadas.containsAll(demandasList)){
            novoUsuario.setId_demandas(demandasAssociadas);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Demanadas Não encontradas");
        }

        if (softSkillsAssociadas.containsAll(softSkillsList)){
            novoUsuario.setId_softskill(softSkillsAssociadas);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SoftSkills Não encontradas");
        }

        if (hardSkillsAssociadas.containsAll(hardSkillsList)){
            novoUsuario.setId_hardskill(hardSkillsAssociadas);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HardSkills Não encontradas");
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));
    }

    @PutMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> editarUsuario(@PathVariable(value = "id") UUID id, @ModelAttribute @Valid UsuariosDTOs usuariosDTOs){
        Optional<UsuarioModel> buscandoUsuario = usuarioRepository.findById(id);

        if (buscandoUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        List<DemandasModel> demandasList = demandasRepository.findAllById(usuariosDTOs.id_demandas());
        Set<DemandasModel> demandasAssociadas = new HashSet<>(demandasList);

        List<SoftSkillsModel> softSkillsList = softSkillsRepository.findAllById(usuariosDTOs.id_softSkills());
        Set<SoftSkillsModel> softSkillsAssociadas = new HashSet<>(softSkillsList);

        List<HardSkillsModel> hardSkillsList = hardSkillsRepository.findAllById(usuariosDTOs.id_hardskills());
        Set<HardSkillsModel> hardSkillsAssociadas = new HashSet<>(hardSkillsList);


        UsuarioModel usuarioEditado = new UsuarioModel();
        BeanUtils.copyProperties(usuariosDTOs, usuarioEditado);

        String urlImagem;

        try{
            urlImagem = fileUploadService.fazerUpload(usuariosDTOs.foto());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        LocalTime horassemanais;
        LocalDate dataferias;

        try{
            horassemanais = converterDataTime.StringToDateTime(usuariosDTOs.horasDaSemana());
            dataferias = converterDataTime.StringToDate(usuariosDTOs.dataDaferias());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        usuarioEditado.setUrl_img(urlImagem);
        usuarioEditado.setHoras_semanais(horassemanais);
        usuarioEditado.setData_ferias(dataferias);

        if (demandasAssociadas.containsAll(demandasList)){
            usuarioEditado.setId_demandas(demandasAssociadas);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Demanadas Não encontradas");
        }

        if (softSkillsAssociadas.containsAll(softSkillsList)){
            usuarioEditado.setId_softskill(softSkillsAssociadas);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SoftSkills Não encontradas");
        }

        if (hardSkillsAssociadas.containsAll(hardSkillsList)){
            usuarioEditado.setId_hardskill(hardSkillsAssociadas);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HardSkills Não encontradas");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") UUID id){

        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        usuarioRepository.delete(usuarioBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");

    }
}
