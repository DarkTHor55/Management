package com.darkthor.Service;

import com.darkthor.Model.User;
import com.darkthor.Request.UserRequest;

import java.util.List;

public interface IUserService {
    User getUser(Long id);
    List<User> getUsers();

}
