package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


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
                userEntity.getRoles().stream().map(UserRoleEntity::getRole).map(KrastevsGymUserDetailsService::map).toList(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getId()
        );
    }

    private static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }
}
