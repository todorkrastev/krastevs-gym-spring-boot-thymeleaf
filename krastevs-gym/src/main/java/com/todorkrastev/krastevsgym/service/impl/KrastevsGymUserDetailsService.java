package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

public class KrastevsGymUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public KrastevsGymUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(KrastevsGymUserDetailsService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return new KrastevsGymUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                List.of(),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }
}
