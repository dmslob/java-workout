package com.dmslob.memento;

public class WordDocument {
	private long id;
	private String title;
	private String heading;
	private String description;

	public WordDocument(long id, String title) {
		this.id = id;
		this.title = title;
	}

	public WordDocumentMemento createMemento() {
		return new WordDocumentMemento(id, title, heading, description);
	}

	public void restore(WordDocumentMemento documentMemento) {
		this.id = documentMemento.getId();
		this.title = documentMemento.getTitle();
		this.heading = documentMemento.getHeading();
		this.description = documentMemento.getDescription();
	}

	@Override
	public String toString() {
		return "Word Document[id=" + id + ", title=" + title + ", heading=" + heading + ", description=" + description + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
