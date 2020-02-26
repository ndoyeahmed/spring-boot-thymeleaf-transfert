package com.mouhamed.transfertargent.transfert.dao;

import com.mouhamed.transfertargent.transfert.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends JpaRepository<OS, Long> {
}
