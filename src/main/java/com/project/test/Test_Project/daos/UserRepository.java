package com.project.test.Test_Project.daos;

import com.project.test.Test_Project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.email = :email")
    User findUserByEmail(@Param("email")String email);

    @Query("SELECT user FROM User user WHERE user.id = :userId")
    User findUserById(@Param("userId")int userId);

}
