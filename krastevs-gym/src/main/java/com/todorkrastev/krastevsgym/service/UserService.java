package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;

public interface UserService {
    boolean doesEmailExist(String email);

    void registerUser(UserRegisterDTO userRegisterDTO);

    UserEntity findUserById(Long currUserId);

    Long findAdminId();

    UserEntity findUserByExerciseId(Long exerciseId);
}
