package com.penda.transfertargent.transfert.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Partenaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String mail;
    private String tel;
    private String ninea;
    @OneToMany(mappedBy = "partenaire")
    private List<Compte>comptes;
    @OneToMany(mappedBy = "partenaire")
    private List<Utilisateur>utilisateurList;
}
