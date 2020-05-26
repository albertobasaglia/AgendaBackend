package com.alberto.agenda.repository;

import com.alberto.agenda.entity.AppuntamentoEntity;
import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppuntamentoRepository extends CrudRepository<AppuntamentoEntity,Long> {
    List<AppuntamentoEntity> findByPersoneContains(PersonaEntity persona);
    AppuntamentoEntity findByIdAndPersoneContains(Long id, PersonaEntity person);
    List<AppuntamentoEntity> findByDataInizioIsBetweenOrDataFineIsBetweenAndPersoneContains(Date dataInizio1, Date dataFine1, Date dataInizio2, Date dataFine2, PersonaEntity persone);
}
