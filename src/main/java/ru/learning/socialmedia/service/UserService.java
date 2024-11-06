package ru.learning.socialmedia.service;


import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.request.UserRequest;
import ru.learning.socialmedia.model.response.UserResponse;

import java.util.List;

public interface UserService
{
    List<UserFullInformation> getAllUsers();
    UserFullInformation getUserById(long userId);
    UserFullInformation createUser(UserRequest userRequest);
    UserFullInformation updateUser(long userId, UserRequest userRequest);
}
