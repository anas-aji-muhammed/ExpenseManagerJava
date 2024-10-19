package com.anasajimuhammed.expensemanagerjava.services;

import com.anasajimuhammed.expensemanagerjava.model.UserDataModel;

public interface AuthenticationService {
    UserDataModel registerNewUser(UserDataModel userData);
}
