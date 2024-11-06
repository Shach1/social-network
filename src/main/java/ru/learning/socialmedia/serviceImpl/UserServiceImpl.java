package ru.learning.socialmedia.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.request.UserRequest;
import ru.learning.socialmedia.repository.UserRepository;
import ru.learning.socialmedia.service.UserService;

import java.util.List;



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
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public UserFullInformation createUser(UserRequest userRequest) {
        //return userRepository.save();
    }

    @Override
    public UserFullInformation updateUser(long userId, UserRequest userRequest) {
        return null;
    }
}
