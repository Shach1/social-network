package ru.learning.socialmedia.model.response;


import lombok.Data;

@Data
public class UserInSearchResponse {
    private long userId;
    private String fullName;
    private String profilePictureUrl;
}
