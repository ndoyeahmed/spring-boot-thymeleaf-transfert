package com.mouhamed.transfertargent.transfert.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Integer montant;
    private String type;
    private String code;

    private float comm;
    private float commExp;
    private float commDest;
    private float commSys;
    private float taxe;

    @ManyToOne
    @JoinColumn(name = "idUserExp")
    private Utilisateur expediteur ;
    @ManyToOne
    @JoinColumn(name = "idUserDest")
    private Utilisateur destinataire ;

    @ManyToOne
    @JoinColumn(name ="idExped" )
    private Client clientExpediteur;
    @ManyToOne
    @JoinColumn(name ="idDest",insertable = false)
    private Client clientDestinataire;
}
