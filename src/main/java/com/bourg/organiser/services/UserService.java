package com.bourg.organiser.services;

import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.entities.User;

import java.util.List;

public interface UserService {

    public abstract List<User> retrieveUsers();

    public abstract UserCommand saveUserCommand(UserCommand userCommand);

}
