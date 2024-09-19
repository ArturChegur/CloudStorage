package org.chegur.nimbus.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.chegur.nimbus.model.UserModel;
import org.chegur.nimbus.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findByName(username);

        if (userModel.isEmpty()) {
            throw new UsernameNotFoundException(username + " was not found");
        }

        return User.builder()
                .username(userModel.get().getName())
                .password(userModel.get().getPassword())
                .roles(userModel.get().getRoles().split(",\\s*"))
                .build();
    }
}
