package com.mouhamed.transfertargent.transfert.services;

import com.mouhamed.transfertargent.transfert.dao.UtilisateurRepository;
import com.mouhamed.transfertargent.transfert.model.Utilisateur;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public boolean changeUserPassword(String username, String newPassword, MultipartFile[] files, String photo) throws Exception {
        try {
            Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
            if (utilisateur != null) {
                utilisateur.setFiles(files);
                utilisateur.setPhoto(photo);
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

    public Utilisateur findByUsername(String login) {
        try {
            return utilisateurRepository.findByUsername(login);
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public Utilisateur connectedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return this.findByUsername(user.getUsername());
    }
}
