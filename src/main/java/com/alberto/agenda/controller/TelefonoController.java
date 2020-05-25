package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.TelefonoEntity;
import com.alberto.agenda.model.TelefonoModel;
import com.alberto.agenda.repository.TelefonoRepository;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/telefono")
public class TelefonoController {
    TelefonoRepository telefonoRepository;
    UserRepository userRepository;
    public TelefonoController(TelefonoRepository telefonoRepository, UserRepository userRepository) {
        this.telefonoRepository = telefonoRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createTelefono(Authentication authentication, @RequestBody TelefonoModel telefonoModel) {
        TelefonoEntity telefonoEntity = new TelefonoEntity();
        telefonoEntity.setNumero(telefonoModel.getNumero());
        PersonaEntity personaEntity =  this.userRepository.findByUsername(authentication.getName());
        telefonoEntity.setPersona(personaEntity);
        telefonoRepository.save(telefonoEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity deleteTelefono(Authentication authentication, @PathVariable String number) {
        PersonaEntity personaEntity =  this.userRepository.findByUsername(authentication.getName());
        TelefonoEntity telefonoEntity = telefonoRepository.findByPersonaAndNumero(personaEntity, number);
        telefonoRepository.delete(telefonoEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/replace")
    public List<TelefonoEntity> replace(Authentication authentication, @RequestBody List<TelefonoModel> telefonoModelsList) {
        PersonaEntity personaEntity = this.userRepository.findByUsername(authentication.getName());
        telefonoRepository.deleteByPersona(personaEntity);
        for(TelefonoModel telefonoModel: telefonoModelsList) {
            TelefonoEntity nuovo = new TelefonoEntity();
            nuovo.setNumero(telefonoModel.getNumero());
            nuovo.setPersona(personaEntity);
            telefonoRepository.save(nuovo);
        }
        return telefonoRepository.findByPersona(personaEntity);
    }

}
