package com.alberto.agenda.controller;

import com.alberto.agenda.entity.AppuntamentoEntity;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping("/viewDate")
    public List<PromemoriaEntity> viewDate(Authentication authentication, @RequestParam String from, @RequestParam String to) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date dateFrom = dateFormat.parse(from);
        Date dateTo = dateFormat.parse(to);
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        List<PromemoriaEntity> lista = promemoriaRepository.findByDataInizioIsBetweenAndPersonaOrDataFineIsBetweenAndPersona(
                dateFrom,
                dateTo,
                personaEntity,
                dateFrom,
                dateTo,
                personaEntity
        );
        return lista;
    }

    @PostMapping("/create")
    public ResponseEntity<PromemoriaEntity> create(Authentication authentication, @Valid @RequestBody PromemoriaModel promemoriaModel) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        PromemoriaEntity promemoriaEntity = new PromemoriaEntity();
        promemoriaEntity.setPersona(personaEntity);
        promemoriaEntity.setRicorrenza(promemoriaModel.getRicorrenza());
        promemoriaEntity.setDescrizione(promemoriaModel.getDescrizione());
        promemoriaEntity.setDataInizio(promemoriaModel.getDataInizio());
        promemoriaEntity.setDataFine(promemoriaModel.getDataFine());
        promemoriaRepository.save(promemoriaEntity);
        return new ResponseEntity(promemoriaEntity, HttpStatus.OK);
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


    @PutMapping("/update/{id}")
    public ResponseEntity<PromemoriaEntity> update(Authentication authentication, @Valid @RequestBody PromemoriaModel promemoriaModel, @PathVariable Long id) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        PromemoriaEntity promemoriaEntity = promemoriaRepository.findByIdAndPersona(id,personaEntity);
        promemoriaEntity.setRicorrenza(promemoriaModel.getRicorrenza());
        promemoriaEntity.setDescrizione(promemoriaModel.getDescrizione());
        promemoriaEntity.setDataInizio(promemoriaModel.getDataInizio());
        promemoriaEntity.setDataFine(promemoriaModel.getDataFine());
        promemoriaRepository.save(promemoriaEntity);
        return new ResponseEntity(promemoriaEntity, HttpStatus.OK);
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
