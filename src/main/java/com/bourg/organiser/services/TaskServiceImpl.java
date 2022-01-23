package com.bourg.organiser.services;

import com.bourg.organiser.commands.TaskCommand;
import com.bourg.organiser.converters.TaskCommandToTask;
import com.bourg.organiser.converters.TaskToTaskCommand;
import com.bourg.organiser.entities.Task;
import com.bourg.organiser.enums.TaskStatus;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.exceptions.UserNotFound;
import com.bourg.organiser.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.BiPredicate;

/**
 * Created by Bourg, Ambrose on 22/01/2022
 */
@Slf4j
@Service
class TaskServiceImpl implements TaskService{

    private final UserRepository userRepository;
    private final TaskCommandToTask taskCommandToTask;
    private final TaskToTaskCommand taskToTaskCommand;

    public TaskServiceImpl(UserRepository userRepository,
                           TaskCommandToTask taskCommandToTask,
                           TaskToTaskCommand taskToTaskCommand){
        this.userRepository = userRepository;
        this.taskCommandToTask = taskCommandToTask;
        this.taskToTaskCommand = taskToTaskCommand;
    }

    static BiPredicate<Task, TaskCommand> checkTasks = (task, taskCommand) -> task.getTaskName().equals(taskCommand.getTaskName()) &&
            task.getTaskStatus().name().equals(taskCommand.getTaskStatus()) &&
            task.getTaskDescription().equals(taskCommand.getTaskDescription());

    @Transactional
    @Override
    public void removeById(Long userId, Long idToDelete) {

        log.info("Starting TaskServiceImpl removeById...");

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Optional<Task> taskOptional = user
                    .getTasks()
                    .stream()
                    .filter(task -> task.getId().equals(idToDelete))
                    .findFirst();

            if (taskOptional.isPresent()) {
                Task taskToDelete = taskOptional.get();
                user.removeTask(taskToDelete);
                userRepository.save(user);

                log.info(String.format("user %s successfully saved", userId));

            }
        }else{

            String message = String.format("UserId %s not found!", userId);

            log.debug(message);

            throw new UserNotFound(message);

        }

        log.info("Ending TaskServiceImpl removeById...");
    }

    @Transactional
    @Override
    public TaskCommand saveTaskCommand(TaskCommand taskCommand) {

        Optional<User> userOptional = this.userRepository.findById(taskCommand.getUserId());

        if (!userOptional.isPresent()) {

            log.error("User not found for id: " + taskCommand.getUserId());

            throw new UserNotFound("User not found for id: " + taskCommand.getUserId());

        } else {

            User user = userOptional.get();

            Optional<Task> taskOptional = user
                    .getTasks()
                    .stream()
                    .filter(task -> task.getId().equals(taskCommand.getId()))
                    .findFirst();

            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                task.setTaskStatus(TaskStatus.valueOf(taskCommand.getTaskStatus()));
                task.setTaskName(taskCommand.getTaskName());
                task.setTaskDescription(taskCommand.getTaskDescription());
            } else {

                Task task = taskCommandToTask.convert(taskCommand);
                user.addTask(task);

            }

            User savedUser = userRepository.save(user);

            Optional<Task> savedTaskOptional = savedUser
                    .getTasks()
                    .stream()
                    .filter(tasks -> tasks.getId()
                            .equals(taskCommand.getId()))
                    .findFirst();

            if(!savedTaskOptional.isPresent()){
                savedTaskOptional = savedUser.getTasks().stream()
                        .filter( task -> checkTasks.test(task, taskCommand) )
                        .findFirst();
            }

            return taskToTaskCommand.convert(savedTaskOptional.get());
        }

    }
}
