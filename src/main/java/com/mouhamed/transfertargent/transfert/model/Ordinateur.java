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
public class Ordinateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String ram;
    private String disque;
    private String processeur;
    private String ecran;
    @ManyToOne
    @JoinColumn(name = "os", referencedColumnName = "id")
    private OS os;
    @OneToMany(mappedBy = "ordinateur")
    @JsonIgnore
    private List<OrdiLogiciel> ordiLogiciels;
}
