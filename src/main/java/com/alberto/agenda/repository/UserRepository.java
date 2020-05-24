package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository<PersonaEntity,Long> {
    PersonaEntity findByUsername(String username);
    List<PersonaEntity> findByUsernameContainsOrEmailContainsOrNomeContainsOrCognomeContains(String username, String email, String nome, String cognome);
    List<PersonaEntity> findAll();
    int countByIdIn(Collection<Long> id);
    List<PersonaEntity> findByIdIn(Collection<Long> id);
}
