package com.dmslob.patterns.behavioral;

import lombok.Getter;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MementoTest {

    /**
     * Need to restore an object back to its previous state
     * (e.g. "undo" or "rollback" operations).
     */
    @Test
    public void should_test_memento() {
        // given
        var editor = new TextEditor();
        var history = new TextEditorHistory();
        // when
        // 1. Initial state
        editor.type("Hello, ");
        history.save(editor.save()); // Save the first state
        // 2. Second state
        editor.type("world!");
        history.save(editor.save()); // Save the second state
        // 3. Third state
        editor.type(" How are you?");
        // 4. Undo to the previous state (second state)
        TextEditorMemento firstUndo = history.undo();
        if (firstUndo != null) {
            editor.restore(firstUndo);
        }
        // 5. Undo again to the initial state (first state)
        TextEditorMemento secondUndo = history.undo();
        if (secondUndo != null) {
            editor.restore(secondUndo);
        }
        // then
        assertThat(editor.getContent()).isEqualTo("Hello, ");
    }

    /**
     * The TextEditor class is the originator.
     * It contains the state (content) that needs to be saved and restored.
     */
    @Getter
    static class TextEditor {
        private String content;

        public TextEditor() {
            this.content = "";
        }

        public void type(String text) {
            this.content += text;
            System.out.printf("Current content: %s%n", this.content);
        }
        // Creates a Memento to save the current state
        public TextEditorMemento save() {
            return new TextEditorMemento(this.content);
        }
        // Restores the state from a Memento
        public void restore(TextEditorMemento memento) {
            this.content = memento.content();
            System.out.printf("Restored content: %s%n", this.content);
        }
    }

    /**
     * Memento: The object that stores the state of the originator.
     * It's an immutable object that contains the state information.
     */
    record TextEditorMemento(String content) {
    }

    /**
     * The History class is the caretaker.
     * It's responsible for managing the collection of mementos.
     * It provides methods to save and retrieve the different states.
     */
    static class TextEditorHistory {
        private final LinkedList<TextEditorMemento> mementos = new LinkedList<>();

        public void save(TextEditorMemento memento) {
            mementos.push(memento);
            System.out.println("Saved a new state.");
        }

        public TextEditorMemento undo() {
            if (!mementos.isEmpty()) {
                System.out.println("Restoring previous state.");
                return mementos.pop();
            }
            return null;
        }
    }
}
