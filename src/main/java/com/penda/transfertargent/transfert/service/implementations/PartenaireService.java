package com.penda.transfertargent.transfert.service.implementations;

import com.penda.transfertargent.transfert.dao.PartenaireRepository;
import com.penda.transfertargent.transfert.model.Partenaire;
import com.penda.transfertargent.transfert.service.interfaces.IPartenaireService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log
public class PartenaireService implements IPartenaireService {

    private PartenaireRepository partenaireRepository;

    // dependency injection with constructor
    public PartenaireService(PartenaireRepository partenaireRepository) {
        this.partenaireRepository = partenaireRepository;
    }

    // save partner method implementation
    public boolean savePartner(Partenaire partenaire) {
        try {
            partenaireRepository.save(partenaire);
            return true;
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return false;
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
