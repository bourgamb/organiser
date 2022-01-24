package com.bourg.organiser.services;

import com.bourg.organiser.converters.TaskCommandToTask;
import com.bourg.organiser.converters.TaskToTaskCommand;
import com.bourg.organiser.entities.Task;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.exceptions.UserNotFound;
import com.bourg.organiser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class TaskServiceImplUT {

    private Task task;
    private User user;
    private UserRepository userRepository;
    private TaskService taskService;

    private final TaskCommandToTask taskCommandToTask;
    private final TaskToTaskCommand taskToTaskCommand;

    public TaskServiceImplUT(){
        this.taskCommandToTask = new TaskCommandToTask();
        this.taskToTaskCommand = new TaskToTaskCommand();
    }

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

        this.userRepository = Mockito.mock(UserRepository.class);

        this.taskService = new TaskServiceImpl(this.userRepository, this.taskCommandToTask,
                this.taskToTaskCommand);

    }

    @Test
    void removeById_user_not_found() {

        //given - user is not found

        //when
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        //then - user not found exception should be thrown
        assertThatThrownBy(
                () -> this.taskService.removeById(1L,2L))
                .isInstanceOf(UserNotFound.class);


    }

    @Test
    void removeById_user_found() {

        //given - user is found
        User user = new User();
        user.setId(3L);
        user.setUserFirstName("A");
        user.setUserSurname("Brown");
        user.setTasks(Arrays.asList(this.task));

        //when
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        this.taskService.removeById(1L,2L);

        //then - user not found exception should not be thrown
        assertDoesNotThrow(
                () -> this.taskService.removeById(1L,2L));


    }

    @Test
    void saveTaskCommand_not_found() {

        //given - user is not found

        //when
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        //then - user not found exception should be thrown
        assertThatThrownBy(
                () -> this.taskService.saveTaskCommand(this.taskToTaskCommand.convert(this.task)))
                .isInstanceOf(UserNotFound.class);

    }
}