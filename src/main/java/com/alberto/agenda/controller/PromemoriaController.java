package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import com.alberto.agenda.repository.PromemoriaRepository;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/promemoria")
public class PromemoriaController {
    UserRepository userRepository;
    PromemoriaRepository promemoriaRepository;
    public PromemoriaController(UserRepository userRepository, PromemoriaRepository promemoriaRepository) {
        this.userRepository = userRepository;
        this.promemoriaRepository = promemoriaRepository;
    }

    @GetMapping("/me")
    public List<PromemoriaEntity> getMine(Authentication authentication) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        List<PromemoriaEntity> lista = promemoriaRepository.findByPersona(personaEntity);
        return lista;
    }
}
