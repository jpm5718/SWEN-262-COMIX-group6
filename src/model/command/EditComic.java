package src.model.command;

import java.util.HashMap;
import java.util.Map;

import src.model.comics.Comic;

public class EditComic implements Command {

    private Comic comic;
    private Map<String, String> changes;
    private Map<String, String> previous;

    public EditComic(Comic comic, Map<String, String> changes) {
        this.comic = comic;
        this.changes = new HashMap<>(changes);
    }

    @Override
    public void execute() {
        previous = setState(changes);
    }

    @Override
    public void undo() {
        setState(previous);
    }

    @Override
    public void redo() {
        execute();
    }

    private Map<String, String> setState(Map<String, String> newState) {
        Map<String, String> currentState = new HashMap<>();

        for (String key : newState.keySet()) {
            try {
                java.lang.reflect.Field field = comic.getClass().getDeclaredField(key);
                field.setAccessible(true);
                currentState.put(key, (String)field.get(comic));
                field.set(comic,newState.get(key));
            } catch (NoSuchFieldException | IllegalAccessException e) {}
        }
        return currentState;
    }

}