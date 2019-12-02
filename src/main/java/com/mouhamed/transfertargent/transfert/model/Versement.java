package com.mouhamed.transfertargent.transfert.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Versement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Integer montant;
    @ManyToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;
    @ManyToOne
    @JoinColumn(name = "idCreateur")
    private Utilisateur utilisateur;
}


