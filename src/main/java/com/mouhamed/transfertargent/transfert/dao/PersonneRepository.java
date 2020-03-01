package com.mouhamed.transfertargent.transfert.dao;

import com.mouhamed.transfertargent.transfert.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
}
