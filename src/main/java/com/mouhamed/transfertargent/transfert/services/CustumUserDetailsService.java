package com.mouhamed.transfertargent.transfert.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mouhamed.transfertargent.transfert.dao.UtilisateurRepository;
import com.mouhamed.transfertargent.transfert.model.Role;
import com.mouhamed.transfertargent.transfert.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//ou @Component
public class CustumUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByLogin(username);
        if (user != null) {
            return new User(user.getLogin(), user.getPwd(),
                    true, true, true, true, getAuthorities(user.getRoles()));
        }
        return null;
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
        }
        return authorities;
    }

}
