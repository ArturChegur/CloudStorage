package org.chegur.nimbus.service;

import lombok.RequiredArgsConstructor;
import org.chegur.nimbus.exception.UserExistsException;
import org.chegur.nimbus.model.entity.User;
import org.chegur.nimbus.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(String email, String username, String password) {

        User user = User.builder()
                .email(email)
                .username(username)
                .password(encoder.encode(password))
                .build();

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistsException("User " + user.getUsername() + " already exists");
        }
    }
}
