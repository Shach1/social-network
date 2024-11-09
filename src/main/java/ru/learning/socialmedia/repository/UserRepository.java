package ru.learning.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.response.UserInSearchResponse;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserFullInformation, Long> {
    UserFullInformation findByUsernameAndPasswordHash(String username, String passwordHash);

    @Query("SELECT e FROM UserFullInformation e WHERE e.fullName LIKE %:name%")
    List<UserFullInformation> findAllByNameContaining(@Param("name") String name);
}
