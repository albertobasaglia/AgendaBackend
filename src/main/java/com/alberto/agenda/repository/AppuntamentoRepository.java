package com.alberto.agenda.repository;

import com.alberto.agenda.entity.AppuntamentoEntity;
import com.alberto.agenda.entity.PersonaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppuntamentoRepository extends CrudRepository<AppuntamentoEntity,Long> {
    List<AppuntamentoEntity> findByPersoneContains(PersonaEntity persona);
    AppuntamentoEntity findByIdAndPersoneContains(Long id, PersonaEntity person);
}
