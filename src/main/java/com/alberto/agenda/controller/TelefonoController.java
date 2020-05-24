package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.TelefonoEntity;
import com.alberto.agenda.model.TelefonoModel;
import com.alberto.agenda.repository.TelefonoRepository;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/telefono")
public class TelefonoController {
    TelefonoRepository telefonoRepository;
    UserRepository userRepository;
    public TelefonoController(TelefonoRepository telefonoRepository, UserRepository userRepository) {
        this.telefonoRepository = telefonoRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("create")
    public ResponseEntity createTelefono(Authentication authentication, @RequestBody TelefonoModel telefonoModel) {
        TelefonoEntity telefonoEntity = new TelefonoEntity();
        telefonoEntity.setNumero(telefonoModel.getNumero());
        PersonaEntity personaEntity =  this.userRepository.findByUsername(authentication.getName());
        telefonoEntity.setPersona(personaEntity);
        telefonoRepository.save(telefonoEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

}
