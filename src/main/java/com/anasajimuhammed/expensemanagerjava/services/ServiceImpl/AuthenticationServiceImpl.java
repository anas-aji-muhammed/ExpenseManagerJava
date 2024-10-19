package com.anasajimuhammed.expensemanagerjava.services.ServiceImpl;

import com.anasajimuhammed.expensemanagerjava.model.UserDataModel;
import com.anasajimuhammed.expensemanagerjava.repository.UserRepository;
import com.anasajimuhammed.expensemanagerjava.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDataModel registerNewUser(UserDataModel userData) {
        userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        return userRepository.save(userData);
    }
}
