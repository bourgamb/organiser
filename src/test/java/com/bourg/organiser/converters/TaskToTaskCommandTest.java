package com.bourg.organiser.converters;

import com.bourg.organiser.commands.TaskCommand;
import com.bourg.organiser.entities.Task;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

class TaskToTaskCommandTest {

    private TaskToTaskCommand taskToTaskCommand;

    @BeforeEach
    void setUp() {
        this.taskToTaskCommand = new TaskToTaskCommand();
    }

    @Test
    void convert() {

        //
        //
        String firstName = "firstName";
        String surname = "surname";
        Long id = 1L;
        String taskName = "testTaskName";
        String taskDesc = "testTaskDescription";

        User user = new User();
        user.setUserFirstName(firstName);
        user.setUserSurname(surname);

        Task task = new Task();
        task.setId(id);
        task.setTaskName(taskName);
        task.setTaskStatus(TaskStatus.COMPLETED);
        task.setTaskDescription(taskDesc);
        task.setUser(user);

        user.setTasks(Arrays.asList(task));

        TaskCommand taskCommand = this.taskToTaskCommand.convert(task);

        assertThat(taskCommand.getId()).isEqualTo(id);
        assertThat(taskCommand.getTaskName()).isEqualTo(taskName);
        assertThat(taskCommand.getTaskDescription()).isEqualTo(taskDesc);
        assertThat(taskCommand.getTaskStatus()).isEqualTo(TaskStatus.COMPLETED.name());
    }
}