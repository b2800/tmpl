package com.esiea.tetris.model.concrete.console.command;

public interface Command {
    public String execute(String[] args);
    public String getName();
}
