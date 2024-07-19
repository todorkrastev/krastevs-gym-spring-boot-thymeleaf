package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;

public interface UserService {
    boolean doesEmailExists(UserRegisterDTO userRegisterDTO);
    void registerUser(UserRegisterDTO userRegisterDTO);

    UserEntity findUserById(Long currUserId);
}
