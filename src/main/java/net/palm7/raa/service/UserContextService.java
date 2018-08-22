package net.palm7.raa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.palm7.raa.model.UserContext;
import net.palm7.raa.repository.UserRepository;

@Service
public class UserContextService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserContextService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserContext loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserContext(repository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException("The e-mail does not exist in database")));
    }

}
