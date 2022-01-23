package com.bourg.organiser;

import com.bourg.organiser.entities.Task;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.repositories.UserRepository;
import com.bourg.organiser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class bootstrap implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Bootstrap some data at start-up

        Task myTask = new Task();

        myTask.setId(3L);
        myTask.setTaskDescription("my dummy task");
        myTask.setTaskName("dummy");

        List<Task> tasks = Arrays.asList(myTask);

        User user = new User();
        user.setId(3L);
        user.setUserFirstName("AB");
        user.setUserSurname("Brown");
        user.setTasks(tasks);

        userRepository.save(user);

        List<User> users = userService.retrieveUsers();

        users.forEach(System.out::println);

    }
}
