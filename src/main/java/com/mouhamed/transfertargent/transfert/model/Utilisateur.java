

package com.mouhamed.transfertargent.transfert.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String pwd;
    private String nomComplet;
    private String email;
    private String photo;
    private String profil;

    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaire;
    @ManyToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;

    @OneToMany(mappedBy = "expediteur")
    private List<Transaction> transactionListExp;
    @OneToMany(mappedBy = "destinataire")
    private List<Transaction> transactionListDest ;

    @OneToMany(mappedBy = "utilisateur")
    private List<Versement> versementList ;
}
