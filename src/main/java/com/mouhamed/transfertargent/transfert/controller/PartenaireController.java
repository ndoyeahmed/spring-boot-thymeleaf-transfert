package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.model.Partenaire;
import com.mouhamed.transfertargent.transfert.services.PartenaireService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;


@Controller
@RequestMapping("/partenaire")
@Log
public class PartenaireController {

    private PartenaireService partenaireService;

    @Autowired
    public void setiPartenaireService(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @GetMapping
    public String listPartenaires() {
        return "partenaire/add";
    }

    @PostMapping
    public ResponseEntity addPartner(@RequestBody Partenaire partenaire) {
        try {
            partenaireService.addPartner(partenaire);
            return ResponseEntity.ok(partenaire);
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500)
                    .body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity getListPartenaire() {
        try {
            return ResponseEntity.ok(Collections.singletonMap("data", partenaireService.getAllPartner()));
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }
}
