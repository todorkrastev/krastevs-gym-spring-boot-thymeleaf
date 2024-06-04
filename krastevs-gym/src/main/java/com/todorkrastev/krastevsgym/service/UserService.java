package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
}
