package com.mouhamed.transfertargent.transfert.services;

import com.mouhamed.transfertargent.transfert.dao.UtilisateurRepository;
import com.mouhamed.transfertargent.transfert.model.Utilisateur;
import lombok.extern.java.Log;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    private BCryptPasswordEncoder encode;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder encode) {
        this.utilisateurRepository = utilisateurRepository;
        this.encode = encode;
    }

    public boolean changeUserPassword(String username, String newPassword) throws Exception {
        try {
            Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
            if (utilisateur != null) {
                utilisateur.setPassword(encode.encode(newPassword));
                utilisateur.setActive(true);
                utilisateurRepository.save(utilisateur);
                return true;
            } else throw new Exception("Username not found");
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public Utilisateur findByLogin(String login) {
        try {
            return utilisateurRepository.findByUsername(login);
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }
}
