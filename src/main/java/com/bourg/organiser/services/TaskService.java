package com.bourg.organiser.services;

import com.bourg.organiser.commands.TaskCommand;

public interface TaskService {

    public abstract void removeById(Long userId, Long idToDelete);

    public abstract TaskCommand saveTaskCommand(TaskCommand taskCommand);
}
