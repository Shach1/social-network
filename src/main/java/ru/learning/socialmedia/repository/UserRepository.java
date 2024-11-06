package ru.learning.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learning.socialmedia.entity.UserFullInformation;

@Repository
public interface UserRepository extends JpaRepository<UserFullInformation, Long> {
    UserFullInformation findByUsernameAndPasswordHash(String username, String passwordHash);

    UserFullInformation findByUsername(String username);
}
