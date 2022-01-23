package com.bourg.organiser.services;

import com.bourg.organiser.commands.TaskCommand;
import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceIT {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void retrieveUsers() {

        List<User> users = userService.retrieveUsers();

        assertThat(users).hasSize(3);
    }

    @Test
    void saveUserCommand() {

        UserCommand userCommand = new UserCommand();
        userCommand.setUserSurname("userSurname");
        userCommand.setUserFirstName("userFirstName");

        TaskCommand taskCommand = new TaskCommand();
        taskCommand.setTaskName("taskName");
        taskCommand.setTaskStatus("IN_PROGRESS");
        taskCommand.setTaskDescription("taskDescription");
        
        userCommand.setTasks(Arrays.asList(taskCommand));

        UserCommand userCommandResult = userService.saveUserCommand(userCommand);

        assertThat(userCommandResult.getUserSurname()).isEqualTo("userSurname");
        assertThat(userCommandResult.getUserFirstName()).isEqualTo("userFirstName");
        assertThat(userCommandResult.getTasks()).hasSize(1);
    }
}