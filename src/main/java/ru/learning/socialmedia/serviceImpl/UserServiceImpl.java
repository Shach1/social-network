package ru.learning.socialmedia.serviceImpl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.request.UserRegistrationRequest;
import ru.learning.socialmedia.model.response.UserInSearchResponse;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.repository.UserRepository;
import ru.learning.socialmedia.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public UserLogInResponse createUser(UserRegistrationRequest user) {
        try{
            UserFullInformation userFullInformation =
                    userRepository.save(buildUserFullInformation(user));
            return buildUserLogInResponse(userFullInformation);
        } catch (Exception e) {
            throw new EntityExistsException(e);
        }
    }

    @Override
    public List<UserInSearchResponse> searchUsersByName(String name) {
        List<UserFullInformation> userList = userRepository.findAllByNameContaining(name);
        if (userList.isEmpty()) {return List.of();}
        return userList
                .stream()
                .map(this::buildUserInSearchResponse)
                .collect(Collectors.toList());
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

    @NonNull
    private UserFullInformation buildUserFullInformation(@NonNull UserRegistrationRequest user)
    {
        UserFullInformation fullUser = new UserFullInformation();
        fullUser.setUsername(user.getUsername());
        fullUser.setEmail(user.getEmail());
        fullUser.setPasswordHash(user.getPasswordHash());
        fullUser.setFullName(user.getFullName());
        fullUser.setBio(user.getBio());
        fullUser.setProfilePictureUrl(user.getProfilePictureUrl());
        fullUser.setDateOfBirth(user.getDateOfBirth());
        return fullUser;
    }

    @NonNull
    private UserInSearchResponse buildUserInSearchResponse(@NonNull UserFullInformation user)
    {
        UserInSearchResponse inSearchUser = new UserInSearchResponse();
        inSearchUser.setUserId(user.getUserId());
        inSearchUser.setFullName(user.getFullName());
        inSearchUser.setProfilePictureUrl(user.getProfilePictureUrl());
        return inSearchUser;
    }
}
