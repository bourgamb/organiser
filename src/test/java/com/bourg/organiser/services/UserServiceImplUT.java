package com.bourg.organiser.services;

import com.bourg.organiser.converters.TaskCommandToTask;
import com.bourg.organiser.converters.TaskToTaskCommand;
import com.bourg.organiser.converters.UserCommandToUser;
import com.bourg.organiser.converters.UserToUserCommand;
import com.bourg.organiser.entities.Task;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
class UserServiceImplUT {

    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;

    private UserServiceImpl userService;
    private UserRepository userRepository;

    public UserServiceImplUT(){
        this.userCommandToUser = new UserCommandToUser(new TaskCommandToTask());
        this.userToUserCommand = new UserToUserCommand(new TaskToTaskCommand());
    }

    @BeforeEach
    void setUp() {
        this.userRepository =  Mockito.mock(UserRepository.class);
        this.userService = new UserServiceImpl(userRepository, this.userCommandToUser, this.userToUserCommand);
    }

    @Test
    void retrieveUsers() {

        User user1 = new User();
        user1.setId(1L);
        user1.setUserSurname("userSurname");
        user1.setUserFirstName("userFirstName");

        Task task = new Task();
        task.setTaskDescription("taskdescription");
        task.setTaskStatus(TaskStatus.COMPLETED);
        task.setTaskName("taskName");
        task.setId(1L);

        user1.setTasks(Arrays.asList(task));
        when(this.userRepository.findAll()).thenReturn(Arrays.asList(user1));

        List<User> users = this.userService.retrieveUsers();

        System.out.println(users);

    }

    @Test
    void saveUserCommand() {
    }
}