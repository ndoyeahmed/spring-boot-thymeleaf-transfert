package com.mouhamed.transfertargent.transfert.controller;

import com.mouhamed.transfertargent.transfert.config.MySimpleUrlAuthenticationSuccessHandler;
import com.mouhamed.transfertargent.transfert.services.UtilisateurService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("change-password")
    public String index() {
        return "change-password";
    }

    @PostMapping("change-password")
    public void changePassword(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(name = "newPassword") String newPassword) throws IOException, ServletException {

        try {
            // handle password change
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            utilisateurService.changeUserPassword(user.getUsername(), newPassword);
            // proceed to the secured page
            handler.proceed(request, response, authentication);
        } catch (Exception e) {
            log.severe(e.getLocalizedMessage());
            response.sendRedirect("/login");
        }
    }
}
