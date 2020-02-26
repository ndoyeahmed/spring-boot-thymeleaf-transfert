package com.mouhamed.transfertargent.transfert.dao;

import com.mouhamed.transfertargent.transfert.model.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OridinateurRepository extends JpaRepository<Ordinateur, Long> {
}
