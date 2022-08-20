package com.project.test.Test_Project.service;

import com.project.test.Test_Project.dtos.UserDTO;
import com.project.test.Test_Project.models.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    @Transactional
    void addUser(User user);

    boolean isUserExists(User user);

    List<UserDTO> getAllUserDTOS();

    UserDTO getUserDTOById(int userId);

    boolean isUserExistsById(int userId);

    User getUserById(int userId);
}
