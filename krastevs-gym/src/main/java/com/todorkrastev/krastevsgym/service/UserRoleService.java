package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;

public interface UserRoleService {
    UserRoleEntity findUserRoleByRole(UserRoleEnum role);
}
