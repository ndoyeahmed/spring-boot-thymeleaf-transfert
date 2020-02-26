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
public class Logiciel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String version;
    @OneToMany(mappedBy = "logiciel")
    @JsonIgnore
    private List<OrdiLogiciel> ordiLogiciels;
}
