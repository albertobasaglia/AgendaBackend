package com.alberto.agenda.repository;

import com.alberto.agenda.entity.TestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestRepository extends CrudRepository<TestEntity,Long> {
    List<TestEntity> findByAge(int age);
    TestEntity findById(long id);
    @Query("SELECT T FROM TestEntity T WHERE T.age < 10")
    TestRepository findByAgeLessThan10();
}
