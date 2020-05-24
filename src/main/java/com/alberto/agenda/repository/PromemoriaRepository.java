package com.alberto.agenda.repository;

import com.alberto.agenda.entity.PersonaEntity;
import com.alberto.agenda.entity.PromemoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PromemoriaRepository extends CrudRepository<PromemoriaEntity,Long> {
    List<PromemoriaEntity> findByPersona(PersonaEntity persona);
    List<PromemoriaEntity> findByDataInizioIsBetweenOrDataFineIsBetween(Date a1, Date b1, Date a2, Date b2);
}
