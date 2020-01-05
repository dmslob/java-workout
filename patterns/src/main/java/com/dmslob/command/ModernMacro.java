package com.dmslob.command;

import com.dmslob.command.canonical.Command;

import java.util.ArrayList;
import java.util.List;

public class ModernMacro {

    private final List<Command> commands = new ArrayList<>();

    public ModernMacro record(Command action) {
        commands.add(action);
        return this;
    }

    public void run() {
        commands.forEach(Command::execute);
    }
}
