package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<PersonaEntity,Long> {
    PersonaEntity findByUsername(String username);
}
