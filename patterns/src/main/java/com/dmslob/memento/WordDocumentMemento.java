package com.dmslob.memento;

public class WordDocumentMemento {

    private final long id;
    private final String title;
    private final String heading;
    private final String description;

    public WordDocumentMemento(long id, String title, String heading, String description) {
        //super();
        this.id = id;
        this.title = title;
        this.heading = heading;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }
}
