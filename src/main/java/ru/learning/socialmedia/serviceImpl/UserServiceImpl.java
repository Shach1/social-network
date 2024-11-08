package ru.learning.socialmedia.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.repository.UserRepository;
import ru.learning.socialmedia.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserFullInformation> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserFullInformation getUserById(long userId) {
        Optional<UserFullInformation> oUserFullInformation = userRepository.findById(userId);
        return oUserFullInformation.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UserLogInResponse logInUser(String username, String passwordHash) {
        UserFullInformation userFullInformation = userRepository.findByUsernameAndPasswordHash(username, passwordHash);
        if (userFullInformation == null) return null;
        return buildUserLogInResponse(userFullInformation);
    }

    @NonNull
    private UserLogInResponse buildUserLogInResponse(@NonNull UserFullInformation user)
    {
        return new UserLogInResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getBio(),
                user.getProfilePictureUrl(),
                user.getDateOfBirth(),
                user.getFollowersCount());
    }

}
