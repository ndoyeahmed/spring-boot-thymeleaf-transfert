package com.penda.transfertargent.transfert.controller;

import com.penda.transfertargent.transfert.model.Partenaire;
import com.penda.transfertargent.transfert.service.interfaces.IPartenaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/partenaire")
public class PartenaireController {

    private IPartenaireService iPartenaireService;

    public PartenaireController(IPartenaireService iPartenaireService) {
        this.iPartenaireService = iPartenaireService;
    }

    @GetMapping
    public String index(Model model){
        Partenaire partenaire = new Partenaire();
        model.addAttribute("partenaire",partenaire);
        model.addAttribute("partenaires", iPartenaireService.getAllPartner());
        return "partenaire/add";
    }
    @PostMapping
    public String index(@Valid @ModelAttribute("partenaire") Partenaire p, Model model){
        model.addAttribute("msg","post active");
        iPartenaireService.savePartner(p);
        model.addAttribute("partenaires", iPartenaireService.getAllPartner());
        return "partenaire/add";
    }

}
