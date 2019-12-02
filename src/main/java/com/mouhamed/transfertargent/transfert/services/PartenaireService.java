package com.mouhamed.transfertargent.transfert.services;

import com.mouhamed.transfertargent.transfert.dao.CompteRepository;
import com.mouhamed.transfertargent.transfert.dao.PartenaireRepository;
import com.mouhamed.transfertargent.transfert.model.Compte;
import com.mouhamed.transfertargent.transfert.model.Partenaire;
import com.mouhamed.transfertargent.transfert.utils.Utilities;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log
public class PartenaireService {

    private PartenaireRepository partenaireRepository;

    private CompteRepository compteRepository;

    // dependency injection with constructor
    public PartenaireService(PartenaireRepository partenaireRepository, CompteRepository compteRepository) {
        this.partenaireRepository = partenaireRepository;
        this.compteRepository = compteRepository;
    }

    // save partner method implementation
    public void addPartner(Partenaire partenaire) throws Exception {
        try {
            partenaireRepository.save(partenaire);
            if (partenaire.getId() != null) {
                Compte compte = new Compte();
                compte.setNumero(Utilities.genereCode(compteRepository.getLastCompteId().orElse(null)));
                compte.setSolde(0);
                compte.setPartenaire(partenaire);
                compteRepository.save(compte);
            } else {
                log.severe("Error on save partner");
                throw new Exception("Error on save partner");
            }
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    // get all partner list method implementation
    public List<Partenaire> getAllPartner() {
        try {
            return partenaireRepository.findAll();
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return new ArrayList<>();
        }
    }
}
