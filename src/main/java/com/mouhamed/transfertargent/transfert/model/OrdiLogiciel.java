package com.mouhamed.transfertargent.transfert.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class OrdiLogiciel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordinateur", referencedColumnName = "id")
    private Ordinateur ordinateur;
    @ManyToOne
    @JoinColumn(name = "logiciel", referencedColumnName = "id")
    private Logiciel logiciel;
}
