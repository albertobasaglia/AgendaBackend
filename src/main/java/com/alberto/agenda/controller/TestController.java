package com.alberto.agenda.controller;

import com.alberto.agenda.entity.TestEntity;
import com.alberto.agenda.repository.TestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    TestRepository repository;
    TestController(TestRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/last")
    public TestEntity getLast() {
        return repository.findById(1);
    }
    @GetMapping("/all")
    public List<TestEntity> getAll() {
        List<TestEntity> entities = new ArrayList<>();
        repository.findAll().forEach(entities::add);
        return entities;
    }
}
