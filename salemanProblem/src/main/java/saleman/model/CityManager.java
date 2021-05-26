package saleman.model;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class CityManager {
	private static final CityManager instance = new CityManager();
	private final ArrayList<City> cities;

	public static CityManager getInstance() {
		return instance;
	}

	private CityManager() {
		this.cities = new ArrayList<>();
	}

	protected void addCity(City city) {
		this.cities.add(city);
	}

	public City getCity(int index) {
		return this.cities.get(index);
	}

	protected ArrayList<City> getCities() {
		return this.cities;
	}

	public int numberOfCities() {
		return this.cities.size();
	}

	public void clearAll() {
		this.cities.clear();
	}

	public void clearOne(int p, Pane root) {
		this.cities.get(p).clear(root);
		this.cities.remove(p);
	}
	public int IndexOf(City city) {
		return cities.indexOf(city);
	}

}
