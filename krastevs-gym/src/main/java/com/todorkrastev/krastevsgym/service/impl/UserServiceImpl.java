package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.UserInfoDTO;
import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.UserRoleService;
import com.todorkrastev.krastevsgym.service.UserService;
import com.todorkrastev.krastevsgym.web.aop.LogRegisterExecution;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.todorkrastev.krastevsgym.util.AppConstants.PROFILE_IMG_URL;


@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleService userRoleService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
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
        return this.userRepository.findAdminByCategory(UserRoleEnum.ADMIN).getId().describeConstable().orElse(null);
    }

    @Override
    public Optional<KrastevsGymUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KrastevsGymUserDetails) {
            return Optional.of((KrastevsGymUserDetails) authentication.getPrincipal());
        }

        return Optional.empty();
    }

    @Override
    public UserInfoDTO getProfile() {
        Long id = getCurrentUser()
                .map(KrastevsGymUserDetails::getCurrId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", getCurrentUser().get().getCurrId()));

        return userRepository.findById(id)
                .map(userEntity -> {
                    UserInfoDTO userInfoDTO = modelMapper.map(userEntity, UserInfoDTO.class);
                    if (userEntity.getImage() == null || userEntity.getImage().isEmpty() || userEntity.getImage().isBlank()) {
                        userInfoDTO.setImage(PROFILE_IMG_URL);
                    }
                    return userInfoDTO;
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public boolean editProfile(UserInfoDTO userInfoDTO) {
        Long id = getCurrentUser()
                .map(KrastevsGymUserDetails::getCurrId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", getCurrentUser().get().getCurrId()));

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        modelMapper.map(userInfoDTO, userEntity);

        userRepository.save(userEntity);

        return !getCurrentUser().get().getEmail().equals(userEntity.getEmail());
    }

    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegisterDTO, UserEntity.class);

        UserRoleEntity userRole = userRoleService.findUserRoleByRole(UserRoleEnum.USER);
        mappedEntity.setRoles(Set.of(userRole));

        mappedEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        return mappedEntity;
    }
}




