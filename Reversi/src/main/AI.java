package main;

import java.util.ArrayList;
import java.util.Random;

public class AI {
	private Bord myBord;
	private int myColor;
	private String myName;
	private int victory;
	private Evaluate evalu = new Evaluate();
	private ArrayList<Integer> pointx = new ArrayList<Integer>();
	private ArrayList<Integer> pointy = new ArrayList<Integer>();
	
	AI(String name, Bord bord){
		myBord = bord;
		myName = name;
	}
	
	public void setColor(int color){
		myColor = color;
	}
	
	public int getColor(){
		return myColor;
	}
	
	public String getName(){
		return myName;
	}
	
	public boolean put(){
		int max = 0, x = 0, y = 0;
		System.out.println("敵が打ちます");
		check();
		if (pointx.size() == 0) return false;
		
		for (int i = 0; i < pointx.size(); i++){
			if(max < evalu.get(pointx.get(i), pointy.get(i))){
				max = evalu.get(pointx.get(i), pointy.get(i));
				x = pointx.get(i);
				y = pointy.get(i);
			}
		}
		
		System.out.println("敵が打ち終わりました " + x + ", " + y);
		return myBord.put(x, y, myColor);
	}
	
	private void check(){
		pointx.clear();
		pointy.clear();
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				if(myBord.putCheck(x, y, myColor)){
					pointx.add(x);
					pointy.add(y);
				}
			}
		}
	}
	
	public void reset(){
		evalu.valueClear(); 
	}
	
	public int getValue(int i){
		return evalu.get(i);
	}
	
	public void add(int num){
		evalu.add(num);
	}
	public void victory(){
		victory++;
	}
	
	public int getVictory(){
		return victory;
	}
	
	public void showEvalu(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.println(evalu.get(i, j));
			}
		}
	}
}
