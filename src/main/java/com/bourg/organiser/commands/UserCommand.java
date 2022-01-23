package com.bourg.organiser.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserCommand {

    private Long id;
    private String userFirstName;
    private String userSurname;
    private List<TaskCommand> tasks = new ArrayList<>();

}
