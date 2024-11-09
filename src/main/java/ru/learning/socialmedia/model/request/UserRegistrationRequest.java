package ru.learning.socialmedia.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistrationRequest
{
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private String bio;
    private String profilePictureUrl;
    private LocalDate dateOfBirth;
}