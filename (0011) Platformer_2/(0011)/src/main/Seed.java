package main;

public class Seed {
	int seedLength;
	char[] seed;
	
	public Seed(int seedLength){
		this.seedLength = seedLength;
		seed = new char[this.seedLength];
		generate();
	}
	
	public char[] generate(){
		for(int i = 0;i < this.seedLength;i++){
			if(Math.random() <= 0.5){ // Choise a letter
				seed[i] = Main.letters[(int)(Math.random() * 22)];
			}else{
				seed[i] = Main.numbers[(int)(Math.random() * 9)];
			}
		}
		return this.seed;
	}
	
	public String getSeed(int sep,String str_){
		String str_seed = "";
		for(int i = 0;i < this.seed.length;i++){
			str_seed += (i % sep == 0 && i != 0) ? str_ + seed[i] : "" + seed[i];
		}
		return str_seed;
	}
}
