package com.sda.school.entity;

import com.sda.school.exception.NullIdException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractService<ID_TYPE, MODEL extends AbstractModel<ID_TYPE>, REPOSITORY extends JpaRepository<MODEL, ID_TYPE>> {

    protected REPOSITORY repository;

    protected Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public AbstractService(REPOSITORY repository) {
        this.repository = repository;
    }

    public List<MODEL> get() {
        log.info("Method get has been called");
        return repository.findAll();
    }

    public Optional<MODEL> get(ID_TYPE id) {
        log.info("Method get has been called, with parameter {}", id);
        return repository.findById(id);
    }

    public MODEL add(MODEL model) {
        log.info("Method add has been called, with parameter {}", model);
        return repository.saveAndFlush(model);
    }

    public Optional<MODEL> update(MODEL model) throws NullIdException {
        log.info("Method update has been called, with parameter {}", model);

        for(int i=0; i<1000; i++) {
            //I am doing some heavy processing here
            log.trace("Current step {}", i);
        }

        if(model.getId() == null) {
            log.error("The provided model has no ID!");
            throw new NullIdException("The provided class ID is null!");
        }
        Optional<MODEL> existingModel = repository.findById(model.getId());
        if(existingModel.isPresent()) {
            log.debug("Model to be updated has been found, and now will proceed with the update");
            return Optional.of(repository.saveAndFlush(model));
        } else {
            log.warn("Model to be updated has not been found. Updating aborted");
            return Optional.empty();
        }
    }

    public void delete(ID_TYPE id) {
        log.info("Proceeding to delete model with id {}", id);
        repository.deleteById(id);
    }

}
