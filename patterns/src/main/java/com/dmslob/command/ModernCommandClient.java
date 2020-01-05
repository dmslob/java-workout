package com.dmslob.command;

import com.dmslob.command.canonical.Document;

public class ModernCommandClient {

    public static void main(String[] args) {

        Document editor = new Document();
        ModernMacro macro = new ModernMacro();

        macro.record(editor::bold)
                .record(editor::italic)
                .record(editor::underline)
                .run();
    }
}
