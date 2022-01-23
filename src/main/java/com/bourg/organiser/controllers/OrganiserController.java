package com.bourg.organiser.controllers;

import com.bourg.organiser.commands.TaskCommand;
import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.services.TaskService;
import com.bourg.organiser.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Slf4j
@RestController
public class OrganiserController {

    private final UserService userService;
    private final TaskService taskService;

    public OrganiserController(UserService userService, TaskService taskService){

        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping(path="/organiser/todos", produces={"application/json"})
    public ResponseEntity<List<User>> getAllUserTasks(){

        log.info("Starting getAllUserTasks...");

        ResponseEntity<List<User>> allUserTasks = new ResponseEntity<>(userService.retrieveUsers(), HttpStatus.OK);

        log.info("Ending getAllUserTasks...");

        return allUserTasks;
    }


    @PostMapping(path="/organiser/users/save", produces={"application/json"}, consumes={"application/json"})
    @ResponseBody
    public ResponseEntity<UserCommand> saveOrUpdateUser(@RequestBody UserCommand userCommand){

        log.info("Starting saveOrUpdateUser...");

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<UserCommand> userCommandResponseEntity = new ResponseEntity<>(userService.saveUserCommand(userCommand), httpHeaders, HttpStatus.OK);

        log.info("Ending saveOrUpdateUser...");

        return userCommandResponseEntity;

    }

    @PostMapping(path="/organiser/todos", produces={"application/json"})
    public ResponseEntity<TaskCommand> saveOrUpdateTask(@RequestBody TaskCommand taskCommand){

        log.info("Starting saveOrUpdateTask...");

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        TaskCommand savedTaskCommand = taskService.saveTaskCommand(taskCommand);

        ResponseEntity<TaskCommand> taskCommandResponseEntity = new ResponseEntity<>(savedTaskCommand, httpHeaders, HttpStatus.OK);

        log.info("Ending saveOrUpdateTask...");

        return taskCommandResponseEntity;
    }

    @PutMapping(path="/organiser/{userId}/todos/{taskId}/remove", produces={"application/json"})
    public ResponseEntity<String> removeTask(@PathVariable String userId,@PathVariable String taskId){

        log.info("Starting removeTask...");

        taskService.removeById(Long.valueOf(userId), Long.valueOf(taskId));

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> removalResponse = new ResponseEntity<>("Successfully removed", httpHeaders, HttpStatus.OK);

        log.info("Ending removeTask...");

        return removalResponse;

    }
}
