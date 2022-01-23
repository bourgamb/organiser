package com.bourg.organiser.converters;

import com.bourg.organiser.commands.UserCommand;
import com.bourg.organiser.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Component
public class UserToUserCommand implements Converter<User, UserCommand>{

    private final TaskToTaskCommand taskToTaskCommand;

    public UserToUserCommand(TaskToTaskCommand taskToTaskCommand){
        this.taskToTaskCommand = taskToTaskCommand;
    }

    @Override
    public UserCommand convert(User source) {

        if(source==null){
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setId(source.getId());
        userCommand.setUserSurname(source.getUserSurname());
        userCommand.setUserFirstName(source.getUserFirstName());

        if(source.getTasks() !=null && source.getTasks().size()>0){
            source.getTasks().forEach( task ->
                    userCommand.getTasks().add(taskToTaskCommand.convert(task)));
        }

        return userCommand;
    }
}
