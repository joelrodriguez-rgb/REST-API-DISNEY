package app.disney.domain.usercase.impl;

import app.disney.common.exceptions.handler.ConflictException;
import app.disney.common.exceptions.handler.NotFoundException;
import app.disney.domain.model.Role;
import app.disney.domain.model.User;
import app.disney.domain.repository.RoleRepository;
import app.disney.domain.repository.UserRepository;
import app.disney.domain.usercase.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userJpaRepository;

    private final RoleRepository roleJpaRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Long ROLE_ADMIN_ID = (long) 1;
    private static final Long ROLE_USER_ID = (long) 2;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userJpaRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name: %s not found" + (email)));
    }

    @Override
    @Transactional
    public User createUser(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new ConflictException("Email address '%s' already exists" + user.getEmail());
        }
        Role role = roleJpaRepository.findById(ROLE_USER_ID).orElseThrow(() -> new NotFoundException(ROLE_USER_ID));
        user.setRole(role);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userJpaRepository.save(user);
    }


    @Override
    @Transactional
    public void updateEntityIfExists(Long id, app.disney.domain.model.User user) {
        userJpaRepository.findById(id).map(userJpa -> {
            Optional.ofNullable(user.getFirstName()).ifPresent(userJpa::setFirstName);
            Optional.ofNullable(user.getLastName()).ifPresent(userJpa::setLastName);
            Optional.ofNullable(user.getPhoto()).ifPresent(userJpa::setPhoto);
            Optional.ofNullable(user.getRole()).ifPresent(userJpa::setRole);

            if (user.getPassword() != null) {
                String encoded = passwordEncoder.encode(user.getPassword());
                userJpa.setPassword(encoded);
            }

            return userJpaRepository.save(userJpa);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userJpaRepository.findById(id).ifPresent(userJpaRepository::delete);
    }

    private boolean existsByEmail(String email) {
        return userJpaRepository.findUserByEmail(email).isPresent();
    }

}
