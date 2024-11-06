package ru.learning.socialmedia.service;


import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.response.UserLogInResponse;

import java.util.List;

public interface UserService
{
    List<UserFullInformation> getAllUsers();
    UserFullInformation getUserById(long userId);

    UserLogInResponse logInUser(String username, String password);
}
