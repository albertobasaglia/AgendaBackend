package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.TelefonoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TelefonoRepository extends CrudRepository<TelefonoEntity,Long> {
    TelefonoEntity findByPersonaAndNumero(PersonaEntity persona, String numero);

    Long deleteByPersona(PersonaEntity persona);

    List<TelefonoEntity> findByPersona(PersonaEntity persona);
}
