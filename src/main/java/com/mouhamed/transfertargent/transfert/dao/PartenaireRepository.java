package com.mouhamed.transfertargent.transfert.dao;

import com.mouhamed.transfertargent.transfert.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
    Optional<Partenaire> findByNinea(String ninea);
}
