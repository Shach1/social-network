package ru.learning.socialmedia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserFullInformation
{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "username") private String username;
    @Column(name = "email") private String email;
    @Column(name = "password_hash") private String passwordHash;
    @Column(name = "full_name") private String fullName;
    @Column(name = "bio") private String bio;
    @Column(name = "profile_picture_url") private String profilePictureUrl;
    @Column(name = "created_at") private String createdAt;
    @Column(name = "date_of_birth") private LocalDate dateOfBirth;
    @Column(name = "followers_count") private int followersCount;
}
