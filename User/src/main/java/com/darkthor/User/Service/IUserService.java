package com.darkthor.User.Service;

import com.darkthor.User.Model.User;
import com.darkthor.User.Request.LoginRequest;
import com.darkthor.User.Request.UserRequest;
import com.darkthor.User.Response.TokenResponse;

public interface IUserService {
    // signup user
    User signup(UserRequest request);
    // login user
    TokenResponse login(LoginRequest request);

}
