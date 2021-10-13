package main;

import java.util.ArrayList;

public class Population {
	ArrayList<City> cities;
	private int size;

	public Population(int size) {
		this.size = size;
		this.cities = new ArrayList<City>();
		for(int i = 0;i < size;i++) {
			this.cities.add(new City(Prob.NormalRandomInt(20, Fenetre.WIDTH - 20),Prob.NormalRandomInt(20, Fenetre.HEIGHT - 20)));
		}
	}

	public Population(Population copy) {
		ArrayList<City> c = new ArrayList<City>();
		for(int i = 0;i < copy.cities.size();i++) {
			c.add(new City(copy.cities.get(i)));
		}
		this.cities = c;
		this.size = copy.size;
	}

	public double totalDistance() {
		double total = 0;
		for(int i = 0;i < this.cities.size();i++) {
			int next = (i == cities.size() - 1) ? 0 : (i + 1);
			total += Main.dist(cities.get(i).getX(), cities.get(i).getY(), cities.get(next).getX(), cities.get(next).getY());
		}
		return total;
	}

	public double getFitness() {
		return 1/totalDistance();
	}

	public void mutate() {
		double mt = 0.4;
		for(int i = 0;i < cities.size();i++) {
			if(Math.random() < mt) {
				swapCityRandom();
			}
		}
	}

	public void swapCityRandom() {
		int rand1;
		int rand2;
		do {
			rand1 = (int)(Math.random()*cities.size());
			rand2 = (int)(Math.random()*cities.size());
		}while(rand1 == rand2);
		int tempX = cities.get(rand1).getX();
		int tempY = cities.get(rand1).getY();
		cities.get(rand1).set(cities.get(rand2).getX(), cities.get(rand2).getY());
		cities.get(rand2).set(tempX, tempY);
	}

	public void swapCityCloser() {
		int rand1;
		rand1 = (int)(Math.random()*cities.size());
		int tempX = cities.get(rand1).getX();
		int tempY = cities.get(rand1).getY();
		int temp_closerX = getCloserCity(cities.get(rand1)).getX();
		int temp_closerY = getCloserCity(cities.get(rand1)).getY();
		System.out.println("Closer : " + cities.indexOf(getCloserCity(cities.get(rand1))));
		System.out.println("SRC : " + rand1);
		cities.get(cities.indexOf(getCloserCity(cities.get(rand1)))).set(tempX, tempY);
		cities.get(rand1).set(temp_closerX, temp_closerY);
	}

	public City getCloserCity(City src) {
		int randInd;
		do {
			randInd = Prob.NormalRandomInt(0, cities.size());
		}while(randInd == cities.indexOf(src));
		City closer = cities.get(randInd);
		for(int i = 1;i < cities.size();i++) {
			if(Main.dist(src.getX(), src.getY(), cities.get(i).getX(), cities.get(i).getY()) < Main.dist(src.getX(), src.getY(), closer.getX(), closer.getY())) {
				closer = cities.get(i);
			}
		}
		return closer;
	}
}
