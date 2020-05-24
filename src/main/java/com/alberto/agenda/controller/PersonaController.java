package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.model.PersonaInfo;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/persona")
public class PersonaController {
    UserRepository userRepository;
    public PersonaController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public PersonaEntity getMe(Authentication authentication){
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        return personaEntity;
    }
    @PutMapping("/update")
    public ResponseEntity updateInfo(Authentication authentication, @RequestBody PersonaInfo personaInfo) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        personaEntity.setEmail(personaInfo.getEmail());
        personaEntity.setNome(personaInfo.getNome());
        personaEntity.setCognome(personaInfo.getCognome());
        userRepository.save(personaEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
