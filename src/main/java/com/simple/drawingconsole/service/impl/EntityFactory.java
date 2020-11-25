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

package com.simple.drawingconsole.service.impl;

import com.simple.drawingconsole.command.DrawEntityCommand;
import com.simple.drawingconsole.command.DrawLineCommand;
import com.simple.drawingconsole.command.DrawRectangleCommand;
import com.simple.drawingconsole.model.Line;
import com.simple.drawingconsole.model.Rectangle;
import com.simple.drawingconsole.service.Entity;

public class EntityFactory {

    public Entity getEntity(DrawEntityCommand command) {
        if (command instanceof DrawLineCommand) {
            DrawLineCommand cmd = (DrawLineCommand) command;
            return new Line(cmd.getX1(), cmd.getY1(), cmd.getX2(), cmd.getY2());
        } else if (command instanceof DrawRectangleCommand) {
            DrawRectangleCommand cmd = (DrawRectangleCommand) command;
            return new Rectangle(cmd.getX1(), cmd.getY1(), cmd.getX2(), cmd.getY2());
        }
        return null;
    }
}
