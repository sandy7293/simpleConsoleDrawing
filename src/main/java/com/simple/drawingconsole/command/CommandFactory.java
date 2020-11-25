/*
 *
 *  * Copyright (c) 2017, Bui Nguyen Thang (Bob), thang.bn@live.com, thangbui.net. All rights reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package com.simple.drawingconsole.command;

import java.util.Arrays;

public class CommandFactory {
    public Command getCommand(String commandLine) throws InvalidCommandException, InvalidCommandParams {
        commandLine = commandLine.trim().replaceAll(" {2}", " ");
        String[] split       = commandLine.split(" ");
        String   mainCommand = split[0].toUpperCase();
        String[] params      = Arrays.copyOfRange(split, 1, split.length);
        switch (mainCommand) {
            case "Q":
                return new QuitCommand();
            case "C":
                return new CreateCommand(params);
            case "L":
                return new DrawLineCommand(params);
            case "R":
                return new DrawRectangleCommand(params);
            default:
                throw new InvalidCommandException();
        }
    }
}