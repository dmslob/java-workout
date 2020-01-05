package com.dmslob.command.canonical;

public class ItalicCommand implements Command {

    private final Editor editor;

    public ItalicCommand(Editor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.italic();
    }
}

