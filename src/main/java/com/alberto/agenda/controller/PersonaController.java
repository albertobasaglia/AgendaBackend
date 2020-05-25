package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.model.PersonaInfo;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/persona")
@CrossOrigin("http://localhost:4200")
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
}
