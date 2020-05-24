package com.alberto.agenda.controller;

import com.alberto.agenda.entity.AppuntamentoEntity;
import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import com.alberto.agenda.model.CreateAppuntamentoModel;
import com.alberto.agenda.repository.AppuntamentoRepository;
import com.alberto.agenda.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/appuntamento")
public class AppuntamentoController {
    UserRepository userRepository;
    AppuntamentoRepository appuntamentoRepository;
    public AppuntamentoController(UserRepository userRepository, AppuntamentoRepository appuntamentoRepository) {
        this.userRepository = userRepository;
        this.appuntamentoRepository = appuntamentoRepository;
    }
    @GetMapping("/view")
    public List<AppuntamentoEntity> viewAll(Authentication authentication) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        List<AppuntamentoEntity> lista = appuntamentoRepository.findByPersoneContains(personaEntity);
        return lista;
    }

    @GetMapping("/view/{id}")
    public AppuntamentoEntity viewById(Authentication authentication, @PathVariable(value = "id") Long id) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        return appuntamentoRepository.findByIdAndPersoneContains(id,personaEntity);
    }


//    @GetMapping("/view/")
//    public List<AppuntamentoEntity>

    @PostMapping("/create")
    public ResponseEntity create(Authentication authentication, @Valid @RequestBody CreateAppuntamentoModel createAppuntamentoModel){
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        //check if all users exist
        int usersInClientList = createAppuntamentoModel.getPersonaIds().size();
        int validUsers = userRepository.countByIdIn(createAppuntamentoModel.getPersonaIds());
        if(validUsers == usersInClientList) {
            List<PersonaEntity> personaEntities = userRepository.findByIdIn(createAppuntamentoModel.getPersonaIds());
            AppuntamentoEntity appuntamentoEntity = new AppuntamentoEntity();
            if(!personaEntities.contains(personaEntity)) {
                personaEntities.add(personaEntity);
            }
            appuntamentoEntity.setPersone(personaEntities);
            appuntamentoEntity.setDescrizione(createAppuntamentoModel.getDescrizione());
            appuntamentoEntity.setDataInizio(createAppuntamentoModel.getDataInizio());
            appuntamentoEntity.setDataFine(createAppuntamentoModel.getDataFine());
            //TODO check collisions
            AppuntamentoEntity addedAppuntamento = appuntamentoRepository.save(appuntamentoEntity);
            System.out.println(addedAppuntamento.getId());
            //aggiungo la roba ad un array che sto scorrendo e mi da ConcurrentException
            //personaEntities.forEach((PersonaEntity p) -> p.getAppuntamenti().add(addedAppuntamento));
            for(int i = 0 ; i < personaEntities.size() ; i++) {
                personaEntities.get(i).getAppuntamenti().add(addedAppuntamento);
                userRepository.save(personaEntities.get(i));
            }
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(Authentication authentication, @PathVariable(value = "id") Long id) {
        PersonaEntity personaEntity = userRepository.findByUsername(authentication.getName());
        AppuntamentoEntity appuntamentoEntity = appuntamentoRepository.findByIdAndPersoneContains(id,personaEntity);
        if(appuntamentoEntity != null) {
            appuntamentoRepository.delete(appuntamentoEntity);
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
