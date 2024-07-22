package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.UserService;
import com.todorkrastev.krastevsgym.web.aop.LogRegisterExecution;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean doesEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @LogRegisterExecution
    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent()) {
            return;
        }

        userRepository.save(map(userRegisterDTO));
    }

    @Override
    public UserEntity findUserById(Long currUserId) {
        return userRepository.findById(currUserId).orElse(null);
    }

    @Override
    public Long findAdminId() {
        return this.userRepository.findAdminByCategory(UserRoleEnum.ADMIN).getId();
    }

    @Override
    public UserEntity findUserByExerciseId(Long exerciseId) {
        return userRepository.findUserByExerciseId(exerciseId);
    }

    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegisterDTO, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        return mappedEntity;
    }
}




