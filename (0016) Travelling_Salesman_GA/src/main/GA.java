package main;

import java.util.ArrayList;

public class GA {
	Population[] populations;
	int size;
	int nbCity;
	public int generation = 0;

	public GA(int size,int nbCity) {
		this.size = size;
		this.nbCity = nbCity;
		populations = new Population[size];
		Population reference = new Population(nbCity);
		for(int i = 0;i < size;i++) {
			populations[i] = new Population(reference);
			populations[i].mutate();
		}	
	}

	public void naturalSelection() {
		populations[0] = new Population(getBest());
		for(int i = 1;i < populations.length;i++) {
			populations[i] = new Population(selectParent());
			populations[i].mutate();
		}
		generation++;
	}

	public double getTotalPopulationsFitness() {
		double total  = 0;
		for(int i = 0;i < populations.length;i++) {
			total += populations[i].getFitness();
		}
		return total;
	}

	Population selectParent() {
		double rand = Prob.NormalRandomDouble(0,getTotalPopulationsFitness());
		double runningSum = 0;

		for (int i = 0; i < populations.length; i++) {
			runningSum += populations[i].getFitness();
			if (runningSum > rand) {
				return populations[i];
			}
		}
		return null;
	}

	public Population getBest() {
		Population best = populations[0];
		for(int i = 0;i < populations.length;i++) {
			if(populations[i].getFitness() > best.getFitness()) {
				best = populations[i];
			}
		}
		return best;
	}

	public Population getWorst() {
		Population worst = populations[0];
		for(int i = 0;i < populations.length;i++) {
			if(populations[i].getFitness() < worst.getFitness()) {
				worst = populations[i];
			}
		}
		return worst;
	}
}
