package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.repository.UserRoleRepository;
import com.todorkrastev.krastevsgym.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DbServiceImpl implements DbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbServiceImpl.class);
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminPass;

    public DbServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.admin.password}") String adminPass) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminPass = adminPass;
    }

    @Override
    public void init() {
        LOGGER.info("------------------------Database startup begins------------------------");

        if (userRepository.count() == 0 & userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            UserRoleEntity savedAdminRole = userRoleRepository.save(adminRole);
            UserRoleEntity savedModeratorRole = userRoleRepository.save(moderatorRole);
            UserRoleEntity savedUserRole = userRoleRepository.save(userRole);

            initAdmin(List.of(savedAdminRole));
            initModerator(List.of(savedModeratorRole));
            initUser(List.of(savedUserRole));
        }


    }

    private void initUser(List<UserRoleEntity> roles) {
        LOGGER.info("------------------------Creating user roles------------------------");

        UserEntity chewbacca = new UserEntity()
                .setFirstName("Chewbacca")
                .setLastName("Krastev-Waller")
                .setAge(9)
                .setWeight(5)
                .setHeight(30)
                .setEmail("chewbacca@web.de")
                .setPassword(passwordEncoder.encode("chewbacca"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(chewbacca);

        UserEntity neli = new UserEntity()
                .setFirstName("Neli")
                .setLastName("Raykova")
                .setAge(68)
                .setWeight(56)
                .setHeight(168)
                .setEmail("neliraykova@web.de")
                .setPassword(passwordEncoder.encode("neliraykova"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(neli);

        UserEntity gabriele = new UserEntity()
                .setFirstName("Gabriele")
                .setLastName("Waller")
                .setAge(60)
                .setWeight(56)
                .setHeight(168)
                .setEmail("gabrielewaller@web.de")
                .setPassword(passwordEncoder.encode("gabrielewaller"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(gabriele);

        UserEntity jeffrey = new UserEntity()
                .setFirstName("Jeffrey")
                .setLastName("Waller")
                .setAge(55)
                .setWeight(82)
                .setHeight(180)
                .setEmail("jeffreywaller@web.de")
                .setPassword(passwordEncoder.encode("jeffreywaller"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(jeffrey);

    }

    private void initModerator(List<UserRoleEntity> roles) {
        LOGGER.info("------------------------Creating a moderator role------------------------");
        UserEntity moderator = new UserEntity()
                .setFirstName("Dominique-Shanecé")
                .setLastName("Waller")
                .setAge(32)
                .setWeight(72)
                .setHeight(178)
                .setEmail("shanece@web.de")
                .setPassword(passwordEncoder.encode("shanece"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(moderator);
    }

    private void initAdmin(List<UserRoleEntity> roles) {
        LOGGER.info("------------------------Creating an admin role-------------------------");
        LOGGER.info("----------------------email: todorkrastev@web.de-----------------------");
        LOGGER.info("-------------------------password: V170sh@229O-------------------------");
        LOGGER.info("-------------Please change the Admin password immediately.-------------");


        UserEntity admin = new UserEntity()
                .setFirstName("Todor")
                .setLastName("Krastev")
                .setAge(34)
                .setWeight(92)
                .setHeight(188)
                .setEmail("todorkrastev@web.de")
                .setPassword(passwordEncoder.encode(adminPass))
                .setRoles(new HashSet<>(roles));

        userRepository.save(admin);
    }
}
