package com.bourg.organiser.services;

import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.converters.UserCommandToUser;
import com.bourg.organiser.converters.UserToUserCommand;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;

    public UserServiceImpl(UserRepository userRepository,
                           UserCommandToUser userCommandToUser,
                           UserToUserCommand userToUserCommand) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }

    @Transactional
    @Override
    public List<User> retrieveUsers() {

        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Transactional
    @Override
    public UserCommand saveUserCommand(UserCommand userCommand) {

        User detachedUser = userCommandToUser.convert(userCommand);

        User savedUser = userRepository.save(detachedUser);

        return userToUserCommand.convert(savedUser);
    }



}
