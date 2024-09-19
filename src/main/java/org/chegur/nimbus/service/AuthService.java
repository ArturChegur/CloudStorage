package org.chegur.nimbus.service;

import lombok.RequiredArgsConstructor;
import org.chegur.nimbus.model.UserModel;
import org.chegur.nimbus.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(UserModel userModel) {
        userModel.setRoles(("USER"));
        userModel.setPassword(encoder.encode(userModel.getPassword()));

        try {
            userRepository.save(userModel);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User with username " + userModel.getName() + " already exists", e);
        }
    }
}
