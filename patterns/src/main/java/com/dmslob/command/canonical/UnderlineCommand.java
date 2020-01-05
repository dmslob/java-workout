package com.dmslob.command.canonical;

public class UnderlineCommand implements Command {

    private final Editor editor;

    public UnderlineCommand(Editor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.underline();
    }
}
