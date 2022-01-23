package com.bourg.organiser.repositories;

import com.bourg.organiser.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
