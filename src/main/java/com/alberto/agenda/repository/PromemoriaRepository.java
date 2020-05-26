package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PromemoriaRepository extends CrudRepository<PromemoriaEntity,Long> {
    List<PromemoriaEntity> findByPersona(PersonaEntity persona);
    List<PromemoriaEntity> findByDataInizioIsBetweenAndPersonaOrDataFineIsBetweenAndPersona(Date dataInizio, Date dataInizio2, PersonaEntity persona, Date dataFine, Date dataFine2, PersonaEntity persona2);
    PromemoriaEntity findByIdAndPersona(Long id, PersonaEntity persona);
}
