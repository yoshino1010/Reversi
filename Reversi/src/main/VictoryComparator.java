package main;

import java.util.Comparator;

public class VictoryComparator implements Comparator<AI>{
	
	public int compare(AI enemy, AI enemy2){
		int num1 = enemy.getVictory();
		int num2 = enemy2.getVictory();
		
		if(num1 > num2){
			return -1;
		}else if(num1 == num2){
			return 0;
		}else{
			return 1;
		}
	}

}