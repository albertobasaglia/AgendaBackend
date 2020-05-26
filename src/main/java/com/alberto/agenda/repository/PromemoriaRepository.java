package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PromemoriaRepository extends CrudRepository<PromemoriaEntity,Long> {
    List<PromemoriaEntity> findByPersona(PersonaEntity persona);
    List<PromemoriaEntity> findByDataInizioIsBetweenOrDataFineIsBetweenAndPersona(Date dataInizio1, Date dataFine1, Date dataInizio2, Date dataFine2, PersonaEntity persona);
    PromemoriaEntity findByIdAndPersona(Long id, PersonaEntity persona);
}
