package ru.learning.socialmedia.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserLogInResponse
{
    private long userId;
    private String username;
    private String email;
    private String fullName;
    private String bio;
    private String profilePictureUrl;
    private LocalDate dateOfBirth;
    private int followersCount;
}
