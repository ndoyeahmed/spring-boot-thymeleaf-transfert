package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.services.PartenaireService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfert/versement")
@Log
public class VersementController {
    private PartenaireService partenaireService;

    @Autowired
    public void setiPartenaireService(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @GetMapping
    public String index() {
        return "versement/versement";
    }
}
