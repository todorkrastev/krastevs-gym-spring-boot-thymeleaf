package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Mock
    private UserRepository mockRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRoleService mockUserRoleService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(
                new ModelMapper(),
                mockPasswordEncoder,
                mockRepository,
                mockUserRoleService
        );
    }

    @Test
    void registerUser() {
        UserRegisterDTO userRegisterDTO =
                new UserRegisterDTO()
                        .setEmail("tdk@mail.com")
                        .setFirstName("Todor")
                        .setLastName("Krastev")
                        .setPassword("top-secret")
                        .setConfirmPassword("top-secret");

        when(mockPasswordEncoder.encode(userRegisterDTO.getPassword()))
                .thenReturn(userRegisterDTO.getPassword() + userRegisterDTO.getPassword());

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);
        when(mockUserRoleService.findUserRoleByRole(UserRoleEnum.USER))
                .thenReturn(userRole);

        userService.registerUser(userRegisterDTO);

        verify(mockRepository).save(userEntityCaptor.capture());

        UserEntity actualSavedEntity = userEntityCaptor.getValue();

        assertEquals(userRegisterDTO.getFirstName(), actualSavedEntity.getFirstName());
        assertEquals(userRegisterDTO.getLastName(), actualSavedEntity.getLastName());
        assertEquals(userRegisterDTO.getPassword() + userRegisterDTO.getPassword(), actualSavedEntity.getPassword());
        assertEquals(userRegisterDTO.getEmail(), actualSavedEntity.getEmail());
        assertEquals(Set.of(userRole), actualSavedEntity.getRoles());
    }

    @Test
    void doesEmailExist() {
        String existingEmail = "existing@example.com";
        String nonExistingEmail = "nonexisting@example.com";

        when(mockRepository.findByEmail(existingEmail)).thenReturn(Optional.of(new UserEntity()));
        when(mockRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        assertTrue(userService.doesEmailExist(existingEmail));
        assertFalse(userService.doesEmailExist(nonExistingEmail));
    }

    @Test
    void findUserById() {
        Long existingUserId = 1L;
        Long nonExistingUserId = 2L;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(existingUserId);

        when(mockRepository.findById(existingUserId)).thenReturn(Optional.of(userEntity));
        when(mockRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        assertEquals(userEntity, userService.findUserById(existingUserId));
        assertNull(userService.findUserById(nonExistingUserId));
    }

    @Test
    void findAdminId() {
        Long adminId = 1L;
        UserEntity adminEntity = new UserEntity();
        adminEntity.setId(adminId);

        when(mockRepository.findAdminByCategory(UserRoleEnum.ADMIN)).thenReturn(adminEntity);

        assertEquals(adminId, userService.findAdminId());
    }

    @Test
    void getCurrentUser() {
        KrastevsGymUserDetails userDetails = mock(KrastevsGymUserDetails.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.setContext(securityContext);

        Optional<KrastevsGymUserDetails> result = userService.getCurrentUser();

        assertTrue(result.isPresent());
        assertEquals(userDetails, result.get());
    }

    @Test
    void getCurrentUser_NoAuthentication() {
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        Optional<KrastevsGymUserDetails> result = userService.getCurrentUser();

        assertTrue(result.isEmpty());
    }

    @Test
    void getCurrentUser_NotKrastevsGymUserDetails() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(new Object());
        SecurityContextHolder.setContext(securityContext);

        Optional<KrastevsGymUserDetails> result = userService.getCurrentUser();

        assertTrue(result.isEmpty());
    }
}