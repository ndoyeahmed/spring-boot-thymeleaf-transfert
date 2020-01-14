

package com.mouhamed.transfertargent.transfert.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String username;
    private String password;
    private String nomComplet;
    private String email;
    private String photo;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "idPartenaire")
//    @JsonIgnore
    private Partenaire partenaire;
    @ManyToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;

    @OneToMany(mappedBy = "expediteur")
    @JsonIgnore
    private List<Transaction> transactionListExp;
    @OneToMany(mappedBy = "destinataire")
    @JsonIgnore
    private List<Transaction> transactionListDest ;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Versement> versementList ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Transient
    private MultipartFile[] files;
}
