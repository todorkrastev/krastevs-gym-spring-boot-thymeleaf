package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KrastevsGymUserDetailsServiceTest {
    private static final String TEST_EMAIL = "ivan@ivan.com";
    private static final String NOT_EXISTENT_EMAIL = "noone@example.com";

    private KrastevsGymUserDetailsService toTest;

    @Mock
    private UserRepository mockRepository;

    @BeforeEach
    void setUp() {
        toTest = new KrastevsGymUserDetailsService(mockRepository);
    }

    @Test
    void loadUserByUsernameUserFound() {
        UserEntity testUser = new UserEntity()
                .setEmail(TEST_EMAIL)
                .setPassword("top-secret")
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setRoles(Set.of(
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));

        when(mockRepository.findByEmail(TEST_EMAIL))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = toTest.loadUserByUsername(TEST_EMAIL);

        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        assertEquals(TEST_EMAIL, userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertEquals(testUser.getFirstName(), krastevsGymUserDetails.getFirstName());
        assertEquals(testUser.getLastName(), krastevsGymUserDetails.getLastName());
        assertEquals(testUser.getFirstName() + " " + testUser.getLastName(), krastevsGymUserDetails.getFullName());

        List<String> expectedRoles = testUser.getRoles().stream().map(UserRoleEntity::getRole).map(r -> "ROLE_" + r).toList();
        List<String> actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void loadUserByUsernameUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_EMAIL)
        );
    }
}