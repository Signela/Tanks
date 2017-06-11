package logic;

import gui.Tile;
import javafx.application.Platform;
import javafx.scene.Group;

public class Shoot implements Runnable, Move {
	private final int STEP = 1;
	private final int WIDTH=800;
	private final int HEIGHT=600;

	private Player player, opponent;
	private Bullet bullet;
	private Tile board[][];
	private Group bulletGroup;
	
	private boolean flageDestroy = true;

	public Shoot(Player player, Player opponent, Tile board[][], Group bulletGroup) {
		bullet = new Bullet(player.getTank(), player.getTank().getX(), player.getTank().getY(),
				player.getTank().getCourse());
		this.player = player;
		this.opponent = opponent;
		this.board = board;
		this.bulletGroup = bulletGroup;
	}

	public Bullet getBullet() {
		return bullet;
	}

	@Override
	public void tryMoveObject() {
		int newX = bullet.getX();
		int newY = bullet.getY();

		if (player.getTank().getCourse() == "UP") {
			newY = bullet.getY() + (STEP * 2);
		}
		if (player.getTank().getCourse() == "DOWN") {
			newY = bullet.getY() - (STEP * 2);
		}
		if (player.getTank().getCourse() == "RIGHT") {
			newX = bullet.getX() + (STEP * 2);
		}
		if (player.getTank().getCourse() == "LEFT") {
			newX = bullet.getX() - (STEP * 2);
		}
		
		if (checkSpace(newX, newY) && collisionWithOpponentTank(newX, newY))
			bullet.moveBullet();
		else if (!collisionWithOpponentTank(newX, newY)) {
			flageDestroy = false;
			opponent.getTank().tankHit(opponent.getNumberPlayer());;
			bulletGroup.getChildren().remove(bullet);
		} else {
			flageDestroy = false;
			bulletGroup.getChildren().remove(bullet);
		}
	}

	@Override
	public boolean collisionWithOpponentTank(int x, int y) {
		if (((x - opponent.getTank().getX()) >= 0 && (x - opponent.getTank().getX()) <= 50)
				&& ((y - opponent.getTank().getY()) >= 0 && (y - opponent.getTank().getY()) <= 50))
			return false;
		return true;
	}
	
	@Override
	public boolean checkObstacle(int x, int y) {
		if(board[toBoard(x)][toBoard(y)].getObstacle())
			return true;
		return false;
	}

	@Override
	public boolean checkAbroad(int x, int y) {
		if (x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT)
			return true;
		return false;
	}
	
	@Override
	public boolean checkSpace(int x, int y) {
		if (checkAbroad(x,y) && checkObstacle(x,y))
			return true;
		return false;
	}

	@Override
	public void run() {
		while (flageDestroy) {
			Platform.runLater(new Runnable() {
				public void run() {
					if (flageDestroy && opponent.getTank()!=null)
						tryMoveObject();
				}
			});
			try {
				Thread.sleep(3);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
