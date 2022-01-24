package com.bourg.organiser.controllers;

import com.bourg.organiser.entities.Task;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.services.TaskService;
import com.bourg.organiser.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Bourg, Ambrose on 22/01/2022
 */
@ExtendWith(MockitoExtension.class)
public class OrganiserControllerTest {

    private Task task;
    private User user;

    @InjectMocks
    OrganiserController organiser;

    @Mock
    UserService userService;

    @Mock
    TaskService taskService;

    @BeforeEach
    void setUp() {

        this.task = new Task();
        task.setId(3L);
        task.setTaskDescription("my dummy task");
        task.setTaskName("dummy");
        task.setTaskStatus(TaskStatus.COMPLETED);

        this.user = new User();
        user.setId(3L);
        user.setUserFirstName("A");
        user.setUserSurname("Brown");
        user.setTasks(Arrays.asList(this.task));
        task.setUser(this.user);

    }

    @Test
    void getAllUserTasks() {

        //given following list of users
        List<User> taskList = Arrays.asList(this.user);

        //when
        when(userService.retrieveUsers()).thenReturn(Arrays.asList(this.user));

        //then
        ResponseEntity<List<User>> allUserTasks = organiser.getAllUserTasks();

        assertThat(allUserTasks.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allUserTasks.getBody()).hasSize(1);

    }

    @Test
    void saveOrUpdateUser() {

        // Todo: similar approach as above...
        // MockMvc tests this function also

    }

    @Test
    void saveOrUpdateTask() {

        // Todo: similar approach as above...
        // MockMvc tests this function also

    }

    @Test
    void removeTask() {

        // Todo: similar approach as above...
        // MockMvc tests this function also
    }
}