package com.bourg.organiser.converters;

import com.bourg.organiser.entities.Task;
import com.bourg.organiser.commands.TaskCommand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Component
public class TaskToTaskCommand implements Converter<Task, TaskCommand> {

    @Synchronized
    @Nullable
    @Override
    public TaskCommand convert(Task source) {

        if(source==null){
            return null;
        }

        final TaskCommand taskCommand = new TaskCommand();
        taskCommand.setId(source.getId());
        taskCommand.setTaskName(source.getTaskName());
        taskCommand.setTaskDescription(source.getTaskDescription());
        taskCommand.setTaskStatus(source.getTaskStatus().name());

        if(source.getUser()!=null){
            taskCommand.setUserId(source.getUser().getId());
        }

        return taskCommand;
    }
}
