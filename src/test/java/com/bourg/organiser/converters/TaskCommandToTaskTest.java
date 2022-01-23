package com.bourg.organiser.converters;

import com.bourg.organiser.entities.Task;
import com.bourg.organiser.commands.TaskCommand;
import com.bourg.organiser.enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


class TaskCommandToTaskTest {

    private TaskCommandToTask taskCommandToTask;

    @BeforeEach
    void setUp() {

        this.taskCommandToTask = new TaskCommandToTask();

    }

    @Test
    void convert() {

        TaskCommand taskCommand = new TaskCommand();
        taskCommand.setId(1L);
        taskCommand.setTaskStatus(TaskStatus.NOT_STARTED.name());
        taskCommand.setTaskDescription("taskDescription");
        taskCommand.setTaskName("taskName");

        Task task = this.taskCommandToTask.convert(taskCommand);

        assertThat(task.getId()).isEqualTo(1L);
        assertThat(task.getTaskName()).isEqualTo("taskName");
        assertThat(task.getTaskDescription()).isEqualTo("taskDescription");
        assertThat(task.getTaskStatus()).isEqualTo(TaskStatus.NOT_STARTED);

    }
}