package behavioral.memento.sample;

import java.util.Stack;

public class History {

    private final Stack<TextMemento> history = new Stack<>();

    public void save(TextMemento memento) {
        history.push(memento);
    }

    public TextMemento undo() {
        if (history.isEmpty()) {
            return null;
        }

        return history.pop();
    }
}
