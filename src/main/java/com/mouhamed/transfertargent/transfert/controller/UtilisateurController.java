package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.config.MySimpleUrlAuthenticationSuccessHandler;
import com.mouhamed.transfertargent.transfert.model.Utilisateur;
import com.mouhamed.transfertargent.transfert.services.UtilisateurService;
import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/")
@Log
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    private MySimpleUrlAuthenticationSuccessHandler handler = new MySimpleUrlAuthenticationSuccessHandler();

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @RequestMapping(value = "image-update")
    @ResponseBody
    public byte[] getImageForUpdate(@RequestParam("name") String imageName) throws IOException {
        File serverFile = new File("D://cours//M2//JEE//image//" + imageName);
        return Files.readAllBytes(serverFile.toPath());
    }

    @GetMapping("connected-user")
    public ResponseEntity<?> connectedUser() {
        try {
            Utilisateur utilisateur = utilisateurService.connectedUser();
            if (utilisateur != null) {
                return ResponseEntity.ok(Collections.singletonMap("response", utilisateur));
            } else {
                return ResponseEntity.ok(Collections.singletonMap("response", null));
            }
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("change-password")
    public String index(Model model) {
        Utilisateur utilisateur = utilisateurService.connectedUser();
        model.addAttribute("imgUrl", utilisateur.getPhoto() != null ? utilisateur.getPhoto() : "default.jpg");
        model.addAttribute("utilisateur", utilisateur);
        return "change-password";
    }

    @PostMapping("change-password")
    public void changePassword(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(name = "newPassword") String newPassword,
                               @ModelAttribute("utilisateur") Utilisateur utilisateur) throws IOException, ServletException {

        try {
            // handle password change
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Utilisateur utilisateur1 = utilisateurService.connectedUser();
            byte[] bytes = {};
            Path path = null;
            if (!utilisateur.getFiles()[0].getName().equals("")) {
                MultipartFile file = utilisateur.getFiles()[0];
                bytes = file.getBytes();
                path = Paths.get("D://cours//M2//JEE//image//" + file.getOriginalFilename());
                utilisateur.setPhoto(file.getOriginalFilename());
            } else {
                utilisateur.setPhoto(utilisateur1.getPhoto());
            }
            // change password and update user after
            utilisateurService.changeUserPassword(utilisateur1.getUsername(), newPassword, utilisateur.getFiles(), utilisateur.getPhoto());
            if (bytes.length != 0) {
                Files.write(path, bytes);
            }

            // proceed to the secured page
            handler.proceed(request, response, authentication);
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            response.sendRedirect("/login");
        }
    }
}
