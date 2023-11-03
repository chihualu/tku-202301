package org.tku.web.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tku.database.entity.User;
import org.tku.database.repository.UserRepository;
import org.tku.web.entity.SessionData;

@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("username: {}", username);
        User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        SessionData sessionData = new SessionData();
        sessionData.setUsername(user.getUserId());
        sessionData.setPassword(user.getPassword());
        return sessionData;
    }
}
