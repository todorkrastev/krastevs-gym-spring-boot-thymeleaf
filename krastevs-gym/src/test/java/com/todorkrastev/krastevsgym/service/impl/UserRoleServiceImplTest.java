package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {

    private UserRoleServiceImpl userRoleService;

    @Mock
    private UserRoleRepository mockRepository;

    @BeforeEach
    void setUp() {
        userRoleService = new UserRoleServiceImpl(mockRepository);
    }

    @Test
    void findUserRoleByRole_UserRoleExists() {
        UserRoleEnum role = UserRoleEnum.USER;
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(role);

        when(mockRepository.findByRole(role)).thenReturn(Optional.of(userRoleEntity));

        UserRoleEntity result = userRoleService.findUserRoleByRole(role);

        assertEquals(userRoleEntity, result);
    }

    @Test
    void findUserRoleByRole_UserRoleNotFound() {
        UserRoleEnum role = UserRoleEnum.ADMIN;

        when(mockRepository.findByRole(role)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> userRoleService.findUserRoleByRole(role));
    }
}

