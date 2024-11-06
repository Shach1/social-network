package ru.learning.socialmedia.model.request;

import lombok.Data;

@Data
public class UserLogInRequest
{
    private String username;
    private String passwordHash;
}
