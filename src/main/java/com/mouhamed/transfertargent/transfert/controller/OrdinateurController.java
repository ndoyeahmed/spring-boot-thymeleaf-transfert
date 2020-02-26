package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.dao.LogicielRepository;
import com.mouhamed.transfertargent.transfert.dao.OridinateurRepository;
import com.mouhamed.transfertargent.transfert.dao.OsRepository;
import com.mouhamed.transfertargent.transfert.model.Ordinateur;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/transfert/ordinateur")
@Log
public class OrdinateurController {
    @Autowired
    private LogicielRepository logicielRepository;

    @Autowired
    private OsRepository osRepository;

    @Autowired
    private OridinateurRepository oridinateurRepository;

    @GetMapping
    public String ordinateurIndex() {
        return "ordinateur/add";
    }

    @PostMapping
    public ResponseEntity<?> addOrdinateur(Ordinateur ordinateur) {
        oridinateurRepository.save(ordinateur);
        return ResponseEntity.ok(ordinateur);
    }

    @GetMapping("/logiciels")
    public ResponseEntity<?> logiciel() {
        return ResponseEntity.ok(logicielRepository.findAll());
    }

    @GetMapping("/os")
    public ResponseEntity<?> osList() {
        return ResponseEntity.ok(osRepository.findAll());
    }

    @GetMapping("/os/{id}")
    public ResponseEntity<?> osById(@PathVariable Long id) {
        return ResponseEntity.ok(osRepository.findById(id));
    }
}
