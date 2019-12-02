package com.mouhamed.transfertargent.transfert.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(mappedBy = "partenaire")
    private List<Compte>comptes;
    @JsonIgnore
    @OneToMany(mappedBy = "partenaire")
    private List<Utilisateur>utilisateurList;
}
