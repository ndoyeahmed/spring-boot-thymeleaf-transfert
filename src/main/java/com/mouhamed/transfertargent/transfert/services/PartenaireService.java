package com.mouhamed.transfertargent.transfert.services;

import com.mouhamed.transfertargent.transfert.dao.CompteRepository;
import com.mouhamed.transfertargent.transfert.dao.PartenaireRepository;
import com.mouhamed.transfertargent.transfert.model.Compte;
import com.mouhamed.transfertargent.transfert.model.Partenaire;
import com.mouhamed.transfertargent.transfert.utils.Utilities;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public boolean addPartner(Partenaire partenaire) throws Exception {
        try {
            System.out.println(partenaire.getUtilisateurList().get(0).getUsername());
            return true;
            /*partenaireRepository.save(partenaire);
            if (partenaire.getId() != null) {
                Compte compte = new Compte();
                compte.setNumero(Utilities.genereCode(compteRepository.getLastCompteId().orElse(null)));
                compte.setSolde(0);
                compte.setPartenaire(partenaire);
                compteRepository.save(compte);
                return true;
            } else {
                log.severe("Error on save partner");
                throw new Exception("Error on save partner");
            }*/
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

    // get a partner by his id
    public Partenaire getPartnerById(Long id) {
        try {
            return partenaireRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return null;
        }
    }

    // only update a partner
    public boolean updatePartner(Partenaire partenaire) {
        try {
            partenaireRepository.save(partenaire);
            return true;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return false;
        }
    }
}
