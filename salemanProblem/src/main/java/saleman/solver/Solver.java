package saleman.solver;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import saleman.model.*;

public class Solver {
	private Tour bestTour = null;
	private double distance;
	private long timeElapsed = 0;

	private void backtrack(ArrayList<City> temp, ArrayList<City> allCities, Pane root) {
		if (allCities.size() == 0) {
			this.Compare(new Tour(temp), root);
		} else {
			for (int i = 0; i < allCities.size(); i++) {
				if ((new Tour(temp)).getDistance() < this.distance) {
					City tmpCity = allCities.get(i);
					temp.add(tmpCity);
					allCities.remove(i);
					backtrack(new ArrayList<City>(temp), allCities, root);
					allCities.add(i, tmpCity);
					temp.remove(temp.size() - 1);
				}
			}
		}
	}

	private void Compare(Tour tour, Pane root) {
		double tmpDistance = tour.getDistance();
		if (tmpDistance < this.distance) {
			this.bestTour = tour;
			this.distance = tmpDistance;
		}
	}

	private void Shortest(Tour tour, Pane root) {
		ArrayList<City> tmp = new ArrayList<City>();
		ArrayList<City> cities = new ArrayList<>(tour.getCities());
		tmp.add(cities.get(0));
		cities.remove(0);
		backtrack(tmp, cities, root);
	}

	public void run(Pane root) {
		long start = System.nanoTime();
		this.clearLine(root);
		this.bestTour = new Tour();
		this.distance = this.bestTour.getDistance();
		Shortest(new Tour(), root);
		this.timeElapsed = System.nanoTime() - start;
		this.displayLine(root, this.bestTour);
		// this.bestTour.printTourLine();
	}

	public void clearLine(Pane root) {
		root.getChildren().removeIf((Node t) -> t.getClass().getSimpleName().equals("Line"));
	}

	private void displayLine(Pane root, Tour btTour) {
		btTour.addLine();
		btTour.display(root);
	}

	public void reset(Pane root) {
		this.bestTour = null;
		this.clearLine(root);
		CityManager.getInstance().clearAll();
		root.getChildren().removeIf((Node t) -> t.getClass().getSimpleName().equals("Line")
				|| t.getClass().getSimpleName().equals("Circle")||t.getClass().getSimpleName().equals("Text"));
	}

	public double getMinDistance() {
		return this.distance;
	}

	public long getTimeElapsed() {
		return this.timeElapsed / 1000000000;
	}

	public void test(Pane root) {
		int[][] cities = { { 100, 200 }, { 300, 120 }, { 234, 340 }, { 120, 210 }, { 222, 444 }, { 666, 654 },
				{ 456, 876 }, { 123, 768 }, { 345, 780 } };
		int i;
		for (i = 0; i < cities.length; i++) {
			new City(cities[i][0], cities[i][1]).display(root);
		}
		this.clearLine(root);
		this.bestTour = new Tour();
		this.distance = this.bestTour.getDistance();
		long start = System.nanoTime();
		Shortest(new Tour(), root);
		this.timeElapsed = System.nanoTime() - start;
		this.displayLine(root, this.bestTour);
		// this.bestTour.printTourLine();
	}
}
