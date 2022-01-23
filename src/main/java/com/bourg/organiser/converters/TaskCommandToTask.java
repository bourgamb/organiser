package com.bourg.organiser.converters;

import com.bourg.organiser.entities.Task;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.commands.TaskCommand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Component
public class TaskCommandToTask implements Converter<TaskCommand, Task> {

    public TaskCommandToTask(){
    }

    // spring does not guarn single threaded
    @Synchronized
    @Nullable
    @Override
    public Task convert(TaskCommand source) {

        if(source == null){
            return  null;
        }

        final Task task = new Task();
        task.setId(source.getId());
        task.setTaskName(source.getTaskName());
        task.setTaskDescription(source.getTaskDescription());

        /*error checking for conversion*/
        task.setTaskStatus(TaskStatus.valueOf(source.getTaskStatus()));
        return task;
    }
}
