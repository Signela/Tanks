package logic;

public class Shoot implements Runnable, Move {

	@Override
	public void tryMoveObject() {
			}

	@Override
	public boolean collisionWithOpponentTank(int x, int y) {
				return true;
	}
	
	@Override
	public boolean checkObstacle(int x, int y) {
		return false;
	}

	@Override
	public boolean checkAbroad(int x, int y) {
				return false;
	}
	
	@Override
	public boolean checkSpace(int x, int y) {
				return false;
	}

	@Override
	public void run() {
			}
}
