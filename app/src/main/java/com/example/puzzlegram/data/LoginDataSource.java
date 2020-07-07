package com.example.puzzlegram.data;

import com.example.puzzlegram.data.model.LoggedInUser;
import com.parse.ParseUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {


    public void logout() {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
    }
}