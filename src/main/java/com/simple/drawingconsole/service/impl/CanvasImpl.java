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

import com.simple.drawingconsole.service.Entity;
import com.simple.drawingconsole.model.InvalidEntityException;
import com.simple.drawingconsole.model.Line;
import com.simple.drawingconsole.model.Rectangle;
import com.simple.drawingconsole.service.Canvas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CanvasImpl implements Canvas {
    private static final char HORIZONTAL_EDGE_CHAR = '-';
    private static final char VERTICAL_EDGE_CHAR   = '|';
    private static final char LINE_CHAR            = 'x';

    private final char[][]           cachedCanvasArray;
    private final int width;
    private final int height;
    // to support advanced operation such as: undo, redo
    private       LinkedList<Entity> entities;
    private final String             horizontalEdge;

    public CanvasImpl(int w, int h) {
        width = w;
        height = h;
        entities = new LinkedList<>();

        cachedCanvasArray = new char[this.height][this.width];
        Arrays.stream(cachedCanvasArray).forEach(chars -> Arrays.fill(chars, ' '));

        horizontalEdge = Stream.generate(() -> String.valueOf(HORIZONTAL_EDGE_CHAR)).limit(width + 2).collect(Collectors.joining());
    }

    @Override
    public void addEntity(Entity entity) throws InvalidEntityException {
        entities.add(entity);
        if (entity instanceof Line) {
            addLine((Line) entity);
        } else if (entity instanceof Rectangle) {
            addRectangle((Rectangle) entity);
        }
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append(horizontalEdge).append("\n");
        for (int i = 0; i < this.height; i++) {
            builder.append(VERTICAL_EDGE_CHAR);
            for (int j = 0; j < this.width; j++) {
                builder.append(cachedCanvasArray[i][j]);
            }
            builder.append(VERTICAL_EDGE_CHAR);
            builder.append("\n");
        }
        builder.append(horizontalEdge);
        return builder.toString();
    }


    private void addRectangle(Rectangle rec) {
        if (isOutside(rec.getX1(), rec.getY1())) {
            throw new InvalidEntityException("Rectangle is outside of canvas");
        }
        drawRectangle(rec.getX1(), rec.getY1(), rec.getX2(), rec.getY2());
    }

    private void addLine(Line line) {
        if (isOutside(line.getX1(), line.getY1())) {
            throw new InvalidEntityException("Line is outside of canvas");
        }

        //trim the part the is outside
        if (line.getX2() >= width) {
            line.setX2(width);
        }
        if (line.getY2() >= height) {
            line.setY2(height);
        }
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        //row by row
        for (int row = y1 - 1; row <= y2 - 1 && row < height; row++) {
            //col by col
            for (int col = x1 - 1; col <= x2 - 1 && col < width; col++) {
                cachedCanvasArray[row][col] = CanvasImpl.LINE_CHAR;
            }
        }
    }


    private void drawRectangle(int x1, int y1, int x2, int y2) {
        //top edge
        drawLine(x1, y1, x2, y1);
        //right edge
        drawLine(x1, y1, x1, y2);
        //bottom edge
        drawLine(x2, y1, x2, y2);
        //right edge
        drawLine(x1, y2, x2, y2);
    }

    private boolean isOutside(int x, int y) {
        return x < 1 || x >= width || y < 1 || y >= height;
    }
}
