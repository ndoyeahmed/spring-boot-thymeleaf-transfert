package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.model.Personne;
import com.mouhamed.transfertargent.transfert.services.PersonneService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfert/personnes")
@Log
@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
public class PersonneController {
    private PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public String index() {
        return "personnes";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPersonne() {
        return ResponseEntity.ok(personneService.findAllPersonne());
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addPersonne(@RequestBody Personne personne) {
        try {
            return ResponseEntity.status(201).body(personneService.addPersonne(personne));
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }
}
