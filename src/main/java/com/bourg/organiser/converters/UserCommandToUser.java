package com.bourg.organiser.converters;

import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.entities.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final TaskCommandToTask taskCommandToTask;

    public UserCommandToUser(TaskCommandToTask taskCommandToTask){
        this.taskCommandToTask = taskCommandToTask;
    }

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {

        if(source==null){
            return null;
        }

        final User user = new User();
        user.setId(source.getId());
        user.setUserSurname(source.getUserSurname());
        user.setUserFirstName(source.getUserFirstName());

        if(source.getTasks() !=null && source.getTasks().size()>0){
            source.getTasks().forEach( task ->
                    user.addTask(taskCommandToTask.convert(task)));
        }

        return user;

    }


}
