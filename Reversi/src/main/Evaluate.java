package main;

import java.util.ArrayList;
import java.util.Random;

public class Evaluate {
	private ArrayList<Integer> value = new ArrayList<Integer>();
	
	Evaluate(){
		Random rand = new Random();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				value.add(rand.nextInt(64));
			}
		}
	}
	
	public int get(int x, int y){
		int index = x + y * 7;
		return value.get(index);
	}
}
