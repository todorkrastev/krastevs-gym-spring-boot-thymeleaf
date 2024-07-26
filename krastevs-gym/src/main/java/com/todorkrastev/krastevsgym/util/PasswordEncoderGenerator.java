package com.todorkrastev.krastevsgym.util;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        String password = "topsecret";
        System.out.println(passwordEncoder.encode(password));
    }
}
