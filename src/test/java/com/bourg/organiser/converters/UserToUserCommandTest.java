package com.bourg.organiser.converters;

import com.bourg.organiser.entities.Task;
import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class UserToUserCommandTest {

    private UserToUserCommand userToUserCommand;

    @BeforeEach
    void setUp() {
        TaskToTaskCommand taskToTaskCommand = new TaskToTaskCommand();
        this.userToUserCommand = new UserToUserCommand(taskToTaskCommand);
    }

    @Test
    void convert() {

        Task task = new Task();
        task.setId(1L);
        task.setTaskName("taskName");
        task.setTaskDescription("taskDescription");
        task.setTaskStatus(TaskStatus.COMPLETED);

        User user = new User();
        user.setUserSurname("userSurname");
        user.setId(1L);
        user.setUserFirstName("userFirstName");

        task.setUser(user);
        user.setTasks(Arrays.asList(task));

        UserCommand userCommand = userToUserCommand.convert(user);
        System.out.println(userCommand.getId());
        System.out.println(userCommand.getUserSurname());
        System.out.println(userCommand.getUserFirstName());
        userCommand.getTasks().stream().forEach( x -> System.out.println(x));

    }
}