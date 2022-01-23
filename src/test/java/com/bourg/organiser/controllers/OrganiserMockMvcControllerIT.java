package com.bourg.organiser.controllers;


import com.bourg.organiser.entities.Task;
import com.bourg.organiser.entities.User;
import com.bourg.organiser.services.TaskService;
import com.bourg.organiser.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bourg, Ambrose on 23/01/2022
 */
@ExtendWith(MockitoExtension.class)
public class OrganiserMockMvcControllerIT {

    MockMvc mockMvc;

    @InjectMocks
    OrganiserController organiser;

    @Mock
    UserService userService;

    @Mock
    TaskService taskService;

    @BeforeEach
    public void setup(){

        mockMvc = MockMvcBuilders.standaloneSetup(organiser).build();

    }

    @Test
    void testController_organiser_save_todos() throws Exception {

        Task task = new Task();

        task.setId(3L);
        task.setTaskDescription("my dummy task");
        task.setTaskName("dummy");

        List<Task> tasks = Arrays.asList(task);

        User user = new User();
        user.setId(3L);
        user.setUserFirstName("A");
        user.setUserSurname("Burton");
        user.setTasks(tasks);

        when(userService.retrieveUsers()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/organiser/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testController_organiser_users_save() throws Exception {

        String jsonBody  = "{\"id\":\"6\",\"userFirstName\":\"Frank\",\"userSurname\":\"Marshall\",\"tasks\":[{\"id\":8, \"taskName\":\"Haircut\",\"taskDescription\":\"Get haircut again!\",\"taskStatus\":\"IN_PROGRESS\"}]}";

        mockMvc.perform(post("/organiser/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testController_organiser_todos() throws Exception {

        String jsonBody  = "{\"id\":\"6\",\"userFirstName\":\"Frank\",\"userSurname\":\"Marshall\",\"tasks\":[{\"id\":8, \"taskName\":\"Haircut\",\"taskDescription\":\"Get haircut again!\",\"taskStatus\":\"IN_PROGRESS\"}]}";

        mockMvc.perform(post("/organiser/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testController_organiser_remove() throws Exception {

        String responseBody = mockMvc.perform(put("/organiser/{userId}/todos/{taskId}/remove", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(responseBody).isEqualTo("Successfully removed");
    }
}
