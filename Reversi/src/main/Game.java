package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game{
	private static final int INDIVIDUALNUM = 100;
	private static final int GENELATIONNUM = 100;
	private Bord myBord = new Bord();
	private Frame myFrame = new Frame("Reversi", myBord);
	private int precedence;
	private ArrayList<AI> enemys = new ArrayList<AI>();
	private int turn = 1;
	
	public void Start(){
		createAI(); //初期個体生成
		//for(int i = 0; i < GENELATIONNUM; i++){
			battle();
			sorted();
			/*select();
			for(int i = 0; i < 10; i+=2){
				cross(enemys.get(i), enemys.get(i+1));
			}*/
		//}
	}
	
	private void battle(){
		for(int i = 0; i < INDIVIDUALNUM; i++){
			AI enemy = enemys.get(i);
			enemy.setColor(Bord.WHITE);
			for(int j = 0; j < INDIVIDUALNUM; j++){
				if(i != j){
					AI enemy2 = enemys.get(j);
					enemy2.setColor(Bord.BLACK);
					//initEnemy(enemy, enemy2);
					while(true){
						int put = 0;
						if(enemy.put()) put++;//駒を置く
						System.out.println("色: " + enemy.getColor());
						update(); //再描画
						if(enemy2.put()) put++; //駒を置く
						System.out.println("色: " + enemy2.getColor());
						update(); //再描画
						if(myBord.checkEnd() || put == 0) break;
					}
					/*勝者判定*/
					if (myBord.victory() == enemy.getColor()){
						System.out.println("勝者: " + enemy.getName() + "(" + enemy.getColor() + ")");
						enemy.victory();
					}else{
						System.out.println("勝者: " + enemy2.getName() + "("+ enemy2.getColor() +")");
						enemy2.victory();
					}
				}
				
				myBord.init();
				update();
			}
		}
	}
	
	private void select(){
		for(int i = 10, j = 10; j < enemys.size(); j++){
			enemys.remove(i);
		}
	}
	
	private void cross(AI enemy, AI enemy2){
		AI enemy3 = new AI("enemy" + 11, myBord);
		Random rnd = new Random();
		enemy3.reset();
		int max = rnd.nextInt(64);
		for(int i = 0; i < max; i++){
			enemy3.add(enemy.getValue(i));
		}
		for(int j = max; j < 64; j++){
			enemy3.add(enemy2.getValue(j));
		}
		enemys.add(enemy3);
	}
	
	private void sorted(){
		Collections.sort(enemys, new VictoryComparator());
	}
	
	private void update(){
		myFrame.repaint();
	}
	private void initEnemy(AI enemy, AI enemy2){
		enemy.setColor(Bord.WHITE);
		enemy2.setColor(Bord.BLACK);
	}
	
	private void createAI(){
		for(int i = 0; i < INDIVIDUALNUM; i++){
			enemys.add(new AI("enemy" + i, myBord));
		}
	}
	
	public void setPrecedence(){
		Random rnd = new Random();
		precedence = rnd.nextInt(2);
	}
}
