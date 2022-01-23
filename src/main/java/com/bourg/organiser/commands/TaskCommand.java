package com.bourg.organiser.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Bourg, Ambrose on 21/01/2022
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TaskCommand {

    private Long id;
    private Long userId;
    private String taskName;
    private String taskDescription;
    private String taskStatus;
}
