package main;

public class Bord {
	private static final int NONE = 0; //何も置いてない
	public static final int WHITE = 1; //白の駒が置いてある
	public static final int BLACK = 2; //黒の駒が置いてある
	private int state[][] = new int[8][8];
	private boolean flag = false;
	
	Bord(){
		init();
	}
	
	public boolean passCheck(int color){
		flag = false;
		boolean check1, check2, check3;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				check1 = checkVertical(i, j, color);
				check2 = checkHorizontal(i, j, color);
				check3 = checkSkew(i, j, color);
				if(check1 || check2 || check3){
					return false;
				}
			}
		}
		return true;
	}
	
	public int victory(){
		int white = 0, black = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(state[i][j] == WHITE){
					white++;
				}else if (state[i][j] == BLACK){
					black++;
				}
			}
		}
		if(white < black){
			return BLACK;
		}else{
			return WHITE;
		}
	}
	
	public boolean putCheck(int x, int y, int color){
		flag = false;
		boolean check1, check2, check3;
		if (state[x][y] == NONE){
			check1 = checkVertical(x, y, color);
			check2 = checkHorizontal(x, y, color);
			check3 = checkSkew(x, y, color);
			if (check1 || check2 || check3){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkEnd(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (state[i][j] == NONE){
					return false;
				}
			}
		}
		return true;
	}
	
	public int countWhite(){
		int count = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(state[i][j] == WHITE){
					count++;
				}
			}
		}
		return count;
	}
	
	public int countBlack(){
		int count = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(state[i][j] == BLACK){
					count++;
				}
			}
		}
		return count;
	}
	
	public int getState(int x, int y){
		return state[x][y];
	}
	
	public boolean put(int x, int y, int color){
		flag = true;
		boolean check1, check2, check3;
		if (state[x][y] == NONE){
			check1 = checkVertical(x, y, color);
			check2 = checkHorizontal(x, y, color);
			check3 = checkSkew(x, y, color);
			if (check1 || check2 || check3){
				state[x][y] = color;
				return true;
			}
		}
		return false;
	}
	
	/*盤面状態初期化*/
	public void init(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				state[i][j] = NONE;
			}
		}
		state[3][3] = WHITE;
		state[4][3] = BLACK;
		state[3][4] = BLACK;
		state[4][4] = WHITE;
	}

	/*縦列チェック*/
	private boolean checkVertical(int x, int y, int color){
		int put = 0;
		if (y != 0){
			if (state[x][y-1] != color && state[x][y-1] != NONE){
				for (int i = y-2; i >= 0; i--){
					if (state[x][i] == color){
						if(flag){
						for (int j = y-1; j > i; j--){
							reverse(x, j ,color);
						}
						}
						put++;
						break;
					}
				}
			}
		}
		
		if (y != 7){
			if (state[x][y+1] != color && state[x][y+1] != NONE){
				for(int i = y+2; i < 8; i++){
					if (state[x][i] == color){
						if(flag){
						for (int j = y+1; j < i; j++){
							reverse(x, j, color);
						}
						}
						put++;
						break;
					}
				}
			}
		}
		if (put > 0) return true;
		return false;
	}
	
	private void reverse(int x, int y, int color){
		//System.out.println(x + ", " + y);
		if (color == WHITE){
			state[x][y] = WHITE;
		}else if(color == BLACK){
			state[x][y] = BLACK;
		}
	}
	
	/*横列チェック*/
	private boolean checkHorizontal(int x, int y, int color){
		int put = 0;
		if (x != 0){
			if (state[x-1][y] != color && state[x-1][y] != NONE){
				for (int i = x-2; i >= 0; i--){
					if (state[i][y] == color){
						if(flag){
							for (int j = x-1; j > i; j--){
								reverse(j, y ,color);
							}
						}
						put++;
						break;
					}
				}
			}
		}
		if (x != 7){
			if (state[x+1][y] != color && state[x+1][y] != NONE){
				for(int i = x+2; i < 8; i++){
					if (state[i][y] == color){
						if(flag){
							for (int j = x+1; j < i; j++){
								reverse(j, y, color);
							}
						}
						put++;
						break;
					}
				}
			}
		}
		if (put > 0) return true;
		return false;
	}
	
	/*斜め列チェック*/
	private boolean checkSkew(int x, int y, int color){
		boolean check1, check2, check3, check4;
		check1 = skewTopRight(x, y, color);
		check2 = skewTopLeft(x, y, color);
		check3 = skewUnderRight(x, y, color);
		check4 = skewUnderLeft(x, y, color);
		if (check1 || check2 || check3 || check4){
			return true;
		}
		return false;
	}
	
	
	/*斜め右上列チェック*/
	private boolean skewTopRight(int x, int y, int color){
		int put = 0;
		
		if (x+1 < 8 && y-1 >= 0){
			if (x+1 < 7 && y-1 > 0){
				if (state[x+1][y-1] != color && state[x+1][y-1] != NONE){
					for (int i = x+2, j = y-2; i < 8 && j >= 0; i++, j--){
						if (state[i][j] == color){
							if(flag){
								for (int i2 = x+1, j2 = y-1; i2 <= i && j2 >= j; i2++, j2--){
									reverse(i2, j2, color);
								}
							}
							put++;
							break;
						}
					}
				}
			}
		}
		if(put > 0) return true;
		return false;
	}
	
	/*斜め左上列チェック*/
	private boolean skewTopLeft(int x, int y, int color){
		int put = 0;
		if (x-1 > 0 && y-1 > 0){
			if (state[x-1][y-1] != color && state[x-1][y-1] != NONE){
				for(int i = x-2, j = y-2; i >= 0 && j >= 0; i--, j--){
					if (state[i][j] == color){
						if(flag){
							for(int i2 = x-1, j2 = y-1; i2 >= i && j2 >= j; i2--, j2--){
								reverse(i2, j2, color);
							}
						}
						put++;
						break;
					}
				}
			}
		}
		if (put > 0) return true;
		return false;
	}
	
	/*斜め右下列チェック*/
	private boolean skewUnderRight(int x, int y, int color){
		int put = 0;
		if (x+1 < 8 && y+1 < 8){
			if (state[x+1][y+1] != color && state[x+1][y+1] != NONE){
				for (int i = x+2, j = y+2; i < 8 && j < 8; i++, j++){
					if(state[i][j] == color){
						if(flag){
							for(int i2 = x+1, j2 = y+1; i2 < i && j2 < j; i2++, j2++){
								reverse(i2, j2, color);
							}
						}
						put++;
						break;
					}
				}
			}
		}
		if (put > 0) return true;
		return false;
	}
	
	/*斜め左下列チェック*/
	private boolean skewUnderLeft(int x, int y, int color){
		int put = 0;
		if (x-1 >= 0 && y+1 < 8){
			if (state[x-1][y+1] != color && state[x-1][y+1] != NONE){
				for (int i = x-2, j = y+2; i >= 0 && j < 8; i--, j++){
					if(state[i][j] == color){
						if(flag){
						for(int i2 = x-1, j2 = y+1; i2 > i && j2 < j; i2--, j2++){
							reverse(i2, j2, color);
						}
						}
						put++;
						break;
					}
				}
			}
		}
		if (put > 0) return true;
		return false;
	}
}
