package com.tesda8.region8.program.registration.service;

public interface RegistrationRequirementsCrudService<U> {

    void create(U dto);
    void update(U dto);
    void delete(Long id);
    U get(Long id);
}
