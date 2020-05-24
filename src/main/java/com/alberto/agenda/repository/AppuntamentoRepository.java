package com.alberto.agenda.repository;

import com.alberto.agenda.entity.AppuntamentoEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppuntamentoRepository extends CrudRepository<AppuntamentoEntity,Long> {
}
