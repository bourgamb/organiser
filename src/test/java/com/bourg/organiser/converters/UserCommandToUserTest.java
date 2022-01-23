package com.bourg.organiser.converters;

import com.bourg.organiser.commands.TaskCommand;
import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.assertj.core.api.Assertions.*;

class UserCommandToUserTest {

    private UserCommandToUser userCommandToUser;

    @BeforeEach
    void setUp() {
        TaskCommandToTask taskCommandToTask = new TaskCommandToTask();
        this.userCommandToUser = new UserCommandToUser(taskCommandToTask);
    }

    @Test
    void convert() throws JsonProcessingException {

        UserCommand userCommand = new UserCommand();
        userCommand.setId(1L);
        userCommand.setUserSurname("userSurName");
        userCommand.setUserFirstName("userFirstName");

        TaskCommand taskCommand = new TaskCommand();
        taskCommand.setId(1L);
        taskCommand.setTaskName("taskName");
        taskCommand.setTaskDescription("taskDescription");
        taskCommand.setTaskStatus(TaskStatus.COMPLETED.name());

        userCommand.setTasks(Arrays.asList(taskCommand));

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(userCommand);
        System.out.println(s);

        User user = userCommandToUser.convert(userCommand);

        assertThat(user.getUserSurname()).isEqualTo("userSurName");
        assertThat(user.getUserFirstName()).isEqualTo("userFirstName");
        assertThat(user.getId()).isEqualTo(1L);
        //assertThat(user.getTasks()).isEqualTo(1L);

    }
}