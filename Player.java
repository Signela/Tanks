package logic;

import gui.Tile;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public class Player implements Runnable,Move {
	private final int STEP = 1;
	private final int TANK_SIZE=50;
	private final int WIDTH=800;
	private final int HEIGHT=600;
	
	private Player opponent;
	private Tile[][] board;
	private Tank myTank;
	private int numberPlayer;
	
	private int rightX,rightY;
	
	public boolean UP = false;
	public boolean DOWN = false;
	public boolean LEFT = false;
	public boolean RIGHT = false;

	public boolean W = false;
	public boolean S = false;
	public boolean D = false;
	public boolean A = false;

	public Player(Tank myTank, int numberPlayer, Tile board[][]) {
		this.myTank = myTank;
		this.numberPlayer = numberPlayer;
		this.board=board;
	}
	
	public void setOpponent(Player opponent){
		this.opponent=opponent;
	}
	
	public void setTankNull(){
		myTank=null;
	}
	
	public Tank getTank(){
		return myTank;
	}
	
	public int getNumberPlayer(){
		return numberPlayer;
	}
	
	public synchronized void keyboard(KeyCode keycode) {
		if (numberPlayer == 2) {
			UP = false;
			DOWN = false;
			RIGHT = false;
			LEFT = false;
			if (keycode == KeyCode.UP)
				UP = true;
			if (keycode == KeyCode.DOWN)
				DOWN = true;
			if (keycode == KeyCode.RIGHT)
				RIGHT = true;
			if (keycode == KeyCode.LEFT)
				LEFT = true;
		}
		if (numberPlayer == 1) {
			W = false;
			A = false;
			S = false;
			D = false;
			if (keycode == KeyCode.W)
				W = true;
			if (keycode == KeyCode.S)
				S = true;
			if (keycode == KeyCode.D)
				D = true;
			if (keycode == KeyCode.A)
				A = true;
		}
	}
	
	@Override
	public synchronized void tryMoveObject() {
		int newX = myTank.getX();
		int newY = myTank.getY();
		
		if (DOWN==true || S==true){
			newY = myTank.getY() + STEP;
			myTank.updateTankImage(KeyCode.DOWN);
			myTank.setCourse(KeyCode.DOWN);
		}
		if (UP==true||W==true){
			newY = myTank.getY() - STEP;
			myTank.updateTankImage(KeyCode.UP);
			myTank.setCourse(KeyCode.UP);
		}	
		if (RIGHT==true ||D==true){
			newX = myTank.getX() + STEP;
			myTank.updateTankImage(KeyCode.RIGHT);
			myTank.setCourse(KeyCode.RIGHT);
		}
		if (LEFT==true||A==true){
			newX = myTank.getX() - STEP;
			myTank.updateTankImage(KeyCode.LEFT);
			myTank.setCourse(KeyCode.LEFT);
		}
		
		if (checkSpace(newX, newY)&&collisionWithOpponentTank(newX, newY))
			myTank.moveTank(newX, newY);
	}
	
	@Override
	public boolean collisionWithOpponentTank(int x, int y) {
		if (Math.abs(x - opponent.getTank().getX()) < TANK_SIZE && Math.abs(y - opponent.getTank().getY()) < TANK_SIZE)
			return false;
		return true;
	}
	
	@Override
	public boolean checkObstacle(int x, int y) {
		if (board[toBoard(x)][toBoard(y)].getObstacle()
				&& board[toBoard(rightX)][toBoard(rightY)].getObstacle()
				&& board[toBoard(x)][toBoard(rightY)].getObstacle()
				&& board[toBoard(rightX)][toBoard(y)].getObstacle())
			return true;
		return false;
	}
	
	@Override
	public boolean checkAbroad(int x, int y) {
		if (x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT && rightY >= 0 && rightY <= HEIGHT && rightX >= 0&& rightX <= WIDTH)
				return true;
		return false;
	}
	
	@Override
	public boolean checkSpace(int x, int y) {
		rightX = x + 50;
		rightY = y + 50;
		
		if(checkAbroad(x,y)&&checkObstacle(x,y))
			return true;
		return false;
	}

	@Override
	public void run() {
		while (opponent.getTank()!=null && myTank!=null) {
			Platform.runLater(new Runnable() {
				public void run() {
					if(opponent.getTank()!=null && myTank!=null)
					tryMoveObject();
				}
			});

			try {
				Thread.sleep(5);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
