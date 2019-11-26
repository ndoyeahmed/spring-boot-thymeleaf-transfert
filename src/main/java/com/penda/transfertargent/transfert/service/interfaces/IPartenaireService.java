package com.penda.transfertargent.transfert.service.interfaces;

import com.penda.transfertargent.transfert.model.Partenaire;

import java.util.List;

public interface IPartenaireService {

    boolean savePartner(Partenaire partenaire);

    List<Partenaire> getAllPartner();
}
