package saleman.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public interface Displayable {
    public abstract void display(Pane root);
    public abstract void clear(Pane root);
}