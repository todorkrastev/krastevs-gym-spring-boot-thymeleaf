package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DbServiceInitializerImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private ExerciseCategoryRepository exerciseCategoryRepository;

    @Mock
    private EquipmentTypeRepository equipmentTypeRepository;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    private DepartmentCategoryRepository departmentCategoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PriceFilterRepository priceFilterRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DbServiceInitializerImpl dbServiceInitializer;

    @BeforeEach
    void setUp() {
        String adminPass = "admin";
        dbServiceInitializer = new DbServiceInitializerImpl(
                userRepository,
                userRoleRepository,
                exerciseRepository,
                exerciseCategoryRepository,
                equipmentTypeRepository,
                pictureRepository,
                productCategoryRepository,
                departmentCategoryRepository,
                productRepository,
                priceFilterRepository,
                employeeRepository,
                passwordEncoder,
                adminPass
        );
    }

    @Test
    void testInitWhenRepositoryIsNotEmpty() {
        when(userRepository.count()).thenReturn(10L);
        when(userRoleRepository.count()).thenReturn(10L);
        when(exerciseRepository.count()).thenReturn(10L);
        when(exerciseCategoryRepository.count()).thenReturn(10L);
        when(equipmentTypeRepository.count()).thenReturn(10L);
        when(pictureRepository.count()).thenReturn(10L);
        when(productCategoryRepository.count()).thenReturn(10L);
        when(departmentCategoryRepository.count()).thenReturn(10L);
        when(productRepository.count()).thenReturn(10L);
        when(priceFilterRepository.count()).thenReturn(10L);
        when(employeeRepository.count()).thenReturn(10L);

        dbServiceInitializer.init();

        verify(userRoleRepository, never()).saveAll(anyList());
        verify(userRepository, never()).saveAll(anyList());
        verify(exerciseRepository, never()).saveAll(anyList());
        verify(exerciseCategoryRepository, never()).saveAll(anyList());
        verify(equipmentTypeRepository, never()).saveAll(anyList());
        verify(pictureRepository, never()).saveAll(anyList());
        verify(productCategoryRepository, never()).saveAll(anyList());
        verify(departmentCategoryRepository, never()).saveAll(anyList());
        verify(productRepository, never()).saveAll(anyList());
        verify(priceFilterRepository, never()).saveAll(anyList());
        verify(employeeRepository, never()).saveAll(anyList());
    }
}