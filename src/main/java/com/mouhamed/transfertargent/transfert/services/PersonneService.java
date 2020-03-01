package com.mouhamed.transfertargent.transfert.services;

import com.mouhamed.transfertargent.transfert.dao.PersonneRepository;
import com.mouhamed.transfertargent.transfert.model.Personne;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class PersonneService {
    private PersonneRepository personneRepository;

    @Autowired
    public void setPersonneRepository(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Transactional
    public Personne addPersonne(Personne personne) {
        try {
            personneRepository.save(personne);
            return personne;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Personne> findAllPersonne() {
        try {
            return personneRepository.findAll();
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return new ArrayList<>();
        }
    }
}
