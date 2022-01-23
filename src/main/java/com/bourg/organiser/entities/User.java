package com.bourg.organiser.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

    private String userFirstName;
    private String userSurname;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /*
    *  Helper addTask method to maintain bidirectional relationship
    *  between User <-> task
    * */
    public User addTask(Task task){
        task.setUser(this);
        this.tasks.add(task);
        return this;
    }

    /*
     *  Helper removeTask method to maintain bidirectional relationship
     *  between User <-> task
     * */
    public User removeTask(Task task){
        task.setUser(null);
        this.tasks.remove(task);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return super.getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", userFirstName='" + userFirstName + '\'' +
                ", userSurnameName='" + userSurname + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}