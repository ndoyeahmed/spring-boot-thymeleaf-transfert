package com.penda.transfertargent.transfert.model;

import lombok.*;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Integer solde;
    @ManyToOne
    @JoinColumn(name="partenaire_id")
    private Partenaire partenaire;
    @OneToMany(mappedBy = "compte")
    private List<Utilisateur> utilisateurs;
}
