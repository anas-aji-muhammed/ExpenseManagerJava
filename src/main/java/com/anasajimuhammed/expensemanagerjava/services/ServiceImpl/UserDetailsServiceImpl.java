package com.anasajimuhammed.expensemanagerjava.services.ServiceImpl;

import com.anasajimuhammed.expensemanagerjava.model.UserDataModel;
import com.anasajimuhammed.expensemanagerjava.model.UserPrinciple;
import com.anasajimuhammed.expensemanagerjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDataModel userData;
        try {
            userData = userRepository.findByEmail(email);
            if(userData==null){
                System.out.println("No user Found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new UserPrinciple(userData);

    }
}
