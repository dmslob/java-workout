package com.dmslob.command.canonical;

public class BoldCommand implements Command {

    private final Editor editor;

    public BoldCommand(Editor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.bold();
    }
}
