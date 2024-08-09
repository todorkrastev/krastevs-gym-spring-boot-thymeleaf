package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.UserInfoDTO;
import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;

import java.util.Optional;

public interface UserService {
    boolean doesEmailExist(String email);

    void registerUser(UserRegisterDTO userRegisterDTO);

    UserEntity findUserById(Long currUserId);

    Long findAdminId();

    Optional<KrastevsGymUserDetails> getCurrentUser();

    UserInfoDTO getProfile();

    boolean editProfile(UserInfoDTO userInfoDTO);
}
