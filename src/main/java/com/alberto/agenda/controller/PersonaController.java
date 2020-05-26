package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.model.FreeCheck;
import com.alberto.agenda.model.PersonaInfo;
import com.alberto.agenda.repository.AppuntamentoRepository;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/persona")
@CrossOrigin("http://localhost:4200")
public class PersonaController {
    UserRepository userRepository;
    AppuntamentoRepository appuntamentoRepository;
    public PersonaController(UserRepository userRepository, AppuntamentoRepository appuntamentoRepository) {
        this.userRepository = userRepository;
        this.appuntamentoRepository = appuntamentoRepository;
    }

    @GetMapping("/me")
    public PersonaEntity getMe(Authentication authentication){
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        return personaEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<PersonaInfo> updateInfo(Authentication authentication, @RequestBody PersonaInfo personaInfo) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        personaEntity.setEmail(personaInfo.getEmail());
        personaEntity.setNome(personaInfo.getNome());
        personaEntity.setCognome(personaInfo.getCognome());
        userRepository.save(personaEntity);
        return new ResponseEntity(personaEntity, HttpStatus.OK);
    }

    private List<PersonaInfo> mapEntityToInfo(List<PersonaEntity> personaEntities) {
        ArrayList<PersonaInfo> personaInfos = new ArrayList<>();
        for(PersonaEntity personaEntity: personaEntities) {
            personaInfos.add(personaEntity.extractInfo());
        }
        return personaInfos;
    }

    @GetMapping("/list")
    public List<PersonaInfo> queryInfo() {
        return mapEntityToInfo(userRepository.findAll());
    }

    @GetMapping("/list/{query}")
    public List<PersonaInfo> queryInfo(@PathVariable String query) {
        List<PersonaEntity> personaEntities = userRepository.findByUsernameContainsOrEmailContainsOrNomeContainsOrCognomeContains(
                query,
                query,
                query,
                query
        );
        return mapEntityToInfo(personaEntities);
    }
    @PutMapping("/free")
    public List<Long> areFree(@RequestBody FreeCheck freeCheck) throws ParseException {
        ArrayList<Long> nonDisponibili = new ArrayList<>();
        for(Long personId: freeCheck.getWho()) {
            PersonaEntity personaEntity = userRepository.findById(personId).get();
            int numeroAppuntamenti = appuntamentoRepository.countByDataInizioIsBetweenAndPersoneContainsOrDataFineIsBetweenAndPersoneContains(
                    freeCheck.getFrom(),
                    freeCheck.getTo(),
                    personaEntity,
                    freeCheck.getFrom(),
                    freeCheck.getTo(),
                    personaEntity
            );
            if(numeroAppuntamenti != 0) {
                nonDisponibili.add(personaEntity.getId());
            }
        }
        return nonDisponibili;
    }
}
