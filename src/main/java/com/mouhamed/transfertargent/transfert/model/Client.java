package com.mouhamed.transfertargent.transfert.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numPiece;
    private String nom;
    private String tel;

    @OneToMany(mappedBy = "clientExpediteur")
    private List<Transaction> transactionListExp;
    @OneToMany(mappedBy = "clientDestinataire")
    private List<Transaction> transactionListDest;
}


