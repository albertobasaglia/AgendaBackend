package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<PersonaEntity,Long> {
    PersonaEntity findByUsername(String username);
    List<PersonaEntity> findByUsernameContainsOrEmailContainsOrNomeContainsOrCognomeContains(String username, String email, String nome, String cognome);
    List<PersonaEntity> findAll();
}
