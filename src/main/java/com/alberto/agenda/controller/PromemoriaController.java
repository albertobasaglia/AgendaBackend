package com.alberto.agenda.controller;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import com.alberto.agenda.model.PromemoriaModel;
import com.alberto.agenda.repository.PromemoriaRepository;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/promemoria")
public class PromemoriaController {
    UserRepository userRepository;
    PromemoriaRepository promemoriaRepository;
    public PromemoriaController(UserRepository userRepository, PromemoriaRepository promemoriaRepository) {
        this.userRepository = userRepository;
        this.promemoriaRepository = promemoriaRepository;
    }

    @GetMapping("/view")
    public List<PromemoriaEntity> viewAll(Authentication authentication) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        List<PromemoriaEntity> lista = promemoriaRepository.findByPersona(personaEntity);
        return lista;
    }

    @GetMapping("/view/{id}")
    public PromemoriaEntity viewById(Authentication authentication, @PathVariable(value = "id") Long id) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        return promemoriaRepository.findByIdAndPersona(id,personaEntity);
    }

    @PostMapping("/create")
    public ResponseEntity create(Authentication authentication, @Valid @RequestBody PromemoriaModel promemoriaModel) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        PromemoriaEntity promemoriaEntity = new PromemoriaEntity();
        promemoriaEntity.setPersona(personaEntity);
        promemoriaEntity.setRicorrenza(promemoriaModel.getRicorrenza());
        promemoriaEntity.setDescrizione(promemoriaModel.getDescrizione());
        promemoriaEntity.setDataInizio(promemoriaModel.getDataInizio());
        promemoriaEntity.setDataFine(promemoriaModel.getDataFine());
        promemoriaRepository.save(promemoriaEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(Authentication authentication, @PathVariable(value = "id") Long id) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        PromemoriaEntity promemoriaEntity = promemoriaRepository.findByIdAndPersona(id,personaEntity);
        if(promemoriaEntity != null) {
            promemoriaRepository.delete(promemoriaEntity);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
