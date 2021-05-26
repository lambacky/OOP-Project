package saleman.model;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Tour implements Displayable {
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<MyLine> tourLine = new ArrayList<MyLine>();


	@Override
	public void display(Pane root) {
		//Task<Void>=new Task<Void>() {
			//public Void call() throws Exception{
		
		
		
		for (int lineIndex = 0; lineIndex < this.tourLine.size(); lineIndex++) {
			
			Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					this.tourLine.get(lineIndex).display(root);
					}
				}));
			//fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
			fiveSecondsWonder.play();
		}
				//try {
				//	Thread.sleep(250);
			//	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}
			//	return null;
			//}
		//};
	//	task.run();
	}

	@Override
	public void clear(Pane root) {
		for (int lineIndex = 0; lineIndex < this.tourLine.size(); lineIndex++) {
			this.tourLine.get(lineIndex).clear(root);
		}
	}

	public Tour() {
		cities = new ArrayList<City>(CityManager.getInstance().getCities());
		for (int cityIndex = 0; cityIndex < CityManager.getInstance().numberOfCities(); cityIndex++) {
			setCity(cityIndex, CityManager.getInstance().getCity(cityIndex));
		}
	}

	public Tour(ArrayList<City> cities) {
		super();
		this.cities = cities;
	}

	public void addLine() {
		for (int cityIndex = 0; cityIndex < this.cities.size(); cityIndex++) {
			this.tourLine
					.add(new MyLine(this.cities.get(cityIndex), this.cities.get((cityIndex + 1) % this.cities.size())));
		}
	}

	private void setCity(int tourPosition, City city) {
		this.cities.set(tourPosition, city);
	}

	public ArrayList<City> getCities() {
		return this.cities;
	}

	protected City getCity(int tourPosition) {
		return this.cities.get(tourPosition);
	}

	public double getDistance() {
		double distance = 0;
		for (int i = 0; i < this.cities.size(); i++) {
			distance += this.getCity(i).getDistanceTo(this.getCity((i + 1) % this.tourSize()));
		}
		return distance;
	}

	private int tourSize() {
		return this.cities.size();
	}

	@Override
	public String toString() {
		String tmp = "";
		for (int i = 0; i < cities.size(); i++) {
			tmp += cities.get(i).toString();
		}
		return tmp;
	}

	public void printTourLine() {
		String tmp = "";
		for (int i = 0; i < tourLine.size(); i++) {
			tmp += "(" + tourLine.get(i).getCity1().getLocation().getX() + ","
					+ tourLine.get(i).getCity1().getLocation().getY() + ")";
			tmp += "--";
			tmp += "(" + tourLine.get(i).getCity2().getLocation().getX() + ","
					+ tourLine.get(i).getCity2().getLocation().getY() + ")";
			tmp += ",";
		}
		System.out.println(tmp);
	}
}
