package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.UserService;
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
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        userRepository.save(map(userRegisterDTO));
    }

    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegisterDTO, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        return mappedEntity;
    }
}
