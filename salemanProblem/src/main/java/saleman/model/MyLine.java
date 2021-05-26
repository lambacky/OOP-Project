package saleman.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class MyLine implements Displayable {

	private City city1;
	private City city2;
	private Line visibleLine;

	@Override
	public void display(Pane root) {
		Line l=new Line(this.city1.getLocation().getX(),this.city1.getLocation().getY(),this.city1.getLocation().getX(),this.city1.getLocation().getY());
		Timeline animation = new Timeline(
		        new KeyFrame(Duration.seconds(0.25), 
		                new KeyValue(l.startXProperty(),this.city2.getLocation().getX()),
		                new KeyValue(l.startYProperty(),this.city2.getLocation().getY())));
		    //animation.setCycleCount(Animation.INDEFINITE);
	        animation.play();
		root.getChildren().add(l);
	}

	@Override
	public void clear(Pane root) {
		root.getChildren().remove(this.visibleLine);
	}

	protected City getCity1() {
		return city1;
	}

	protected City getCity2() {
		return this.city2;
	}

	public MyLine(City city1, City city2) { // construct using 2 cities
		super();
		this.city1 = city1;
		this.city2 = city2;
		this.visibleLine = get_line();
	}

	public MyLine() {
		super();
	}

	private Line get_line() { // get line with default color, width
		Line new_line = new Line();
		Color default_color = Color.CORNFLOWERBLUE;
		int default_width = 3;
		
		new_line.startXProperty().bind(this.city1.circle.centerXProperty());
		new_line.startYProperty().bind(this.city1.circle.centerYProperty());
		new_line.endXProperty().bind(this.city2.circle.centerXProperty());
		new_line.endYProperty().bind(this.city2.circle.centerYProperty());
		
		
		//new_line.setStartX(this.city1.getLocation().getX());
		//new_line.setStartY(this.city1.getLocation().getY());
		//new_line.setEndX(this.city2.getLocation().getX());
		//new_line.setEndY(this.city2.getLocation().getY());
		new_line.setStroke(default_color); // set line color
		new_line.setStrokeWidth(default_width); // set line width
		return new_line;
	}

	private Line get_line(int width, Color color) { // get line with input width, color
		Line new_line = new Line();
		new_line.setStartX(this.city1.getLocation().getX());
		new_line.setStartY(this.city1.getLocation().getY());
		new_line.setEndX(this.city2.getLocation().getX());
		new_line.setEndY(this.city2.getLocation().getY());
		new_line.setStroke(color); // set line color
		new_line.setStrokeWidth(width); // set line width
		return new_line;
	}
}
