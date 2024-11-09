package ru.learning.socialmedia.service;


import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.response.UserInSearchResponse;
import ru.learning.socialmedia.model.request.UserRegistrationRequest;
import ru.learning.socialmedia.model.response.UserLogInResponse;

import java.util.List;

public interface UserService
{
    List<UserFullInformation> getAllUsers();
    UserFullInformation getUserById(long userId);
    UserLogInResponse createUser(UserRegistrationRequest user);
    List<UserInSearchResponse> searchUsersByName(String name);

    UserLogInResponse logInUser(String username, String password);
}
