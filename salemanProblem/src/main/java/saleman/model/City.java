package saleman.model;



import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class City implements  Displayable {
    private final Point2D location;
    final Circle circle;
    private final Text text;

    public City(double x, double y) {
        this.location = new Point2D(x, y);
        this.circle = new Circle(x, y, 20,Color.LIGHTGREEN);
        CityManager.getInstance().addCity(this);
        this.text=new Text(String.valueOf(CityManager.getInstance().IndexOf(this)));
        text.setFont(Font.font ("Verdana", 20));
        text.setX(this.getLocation().getX()-text.getLayoutBounds().getWidth() / 2 );
        text.setY(this.getLocation().getY()+text.getLayoutBounds().getHeight() / 4 );
    }

    @Override
    public void display(Pane root) {
        
    	root.getChildren().addAll(this.circle,this.text);//new Text(String.valueOf(CityManager.getInstance().IndexOf(this))));

    }

    @Override
    public void clear(Pane root) {
        root.getChildren().removeAll(this.circle,this.text);
    }


    protected double getDistanceTo(City city) {
        return this.getLocation().distance(city.getLocation());
    }

    protected Point2D getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return "(" + (int) this.location.getX() + "," + (int) this.location.getY() + ")";
    }

}