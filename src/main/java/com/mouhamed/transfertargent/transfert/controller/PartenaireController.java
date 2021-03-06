package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.model.Partenaire;
import com.mouhamed.transfertargent.transfert.services.PartenaireService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@Controller
@RequestMapping("/transfert/partenaire")
@Log
@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
public class PartenaireController {

    private PartenaireService partenaireService;

    @Autowired
    public void setiPartenaireService(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping
    public String listPartenaires() {
        return "partenaire/add";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping
    public ResponseEntity addPartner(@RequestBody Partenaire partenaire) {
        try {
            partenaireService.addPartner(partenaire);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500)
                    .body(e.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/list")
    public ResponseEntity getListPartenaire() {
        try {
            return ResponseEntity.ok(Collections.singletonMap("data", partenaireService.getAllPartner()));
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getPartnerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(partenaireService.getPartnerById(id));
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PutMapping
    public ResponseEntity updatePartner(@RequestBody Partenaire partenaire) {
        try {
            boolean result = partenaireService.updatePartner(partenaire);
            return ResponseEntity.ok(Collections.singletonMap("success", result));
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/search/{ninea}")
    public ResponseEntity<?> searchByNinea(@PathVariable String ninea) {
        try {
            return ResponseEntity.ok(partenaireService.findByNinea(ninea));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }
}
