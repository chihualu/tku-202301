package org.tku.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tku.web.entity.SessionData;

public class UserDetailsServiceImpl implements UserDetailsService {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SessionData sessionData = new SessionData();
        sessionData.setUsername(username);
        sessionData.setPassword(passwordEncoder.encode("123456"));
        return sessionData;
    }
}
