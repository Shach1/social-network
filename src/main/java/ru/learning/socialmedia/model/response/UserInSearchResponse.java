package ru.learning.socialmedia.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInSearchResponse {
    private long userId;
    private String fullName;
    private String profilePictureUrl;
}
