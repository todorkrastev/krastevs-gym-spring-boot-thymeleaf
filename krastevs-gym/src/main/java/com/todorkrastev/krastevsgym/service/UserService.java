package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;

public interface UserService {
    boolean doesEmailExists(UserRegisterDTO userRegisterDTO);
    void registerUser(UserRegisterDTO userRegisterDTO);
}
