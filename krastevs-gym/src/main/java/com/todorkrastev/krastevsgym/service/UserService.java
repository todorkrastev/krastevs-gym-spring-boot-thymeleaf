package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.UserLoginDTO;
import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);
}
