package com.project.test.Test_Project.service;

import com.project.test.Test_Project.daos.UserRepository;
import com.project.test.Test_Project.dtos.UserDTO;
import com.project.test.Test_Project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void addUser(final User user){

        User savedUser=User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        userRepository.save(savedUser);

        return;
    }

    @Override
    public boolean isUserExists(final User user){

        if(userRepository.findUserByEmail(user.getEmail())!=null){

            return true;
        }else{

            return false;
        }
    }

    @Override
    public List<UserDTO>getAllUserDTOS(){

        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private UserDTO toDTO(User user){

        UserDTO userDTO=UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return userDTO;
    }

    @Override
    public UserDTO getUserDTOById(final int userId){

        User user=userRepository.findUserById(userId);

        if(user==null){

            return null;
        }else{

            return toDTO(user);
        }

    }

    @Override
    public boolean isUserExistsById(final int userId){

        if(userRepository.findUserById(userId)!=null){

            return true;
        }else{

            return false;
        }
    }

    @Override
    public User getUserById(final int userId){

        return userRepository.findUserById(userId);
    }

}
