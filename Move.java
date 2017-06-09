package logic;

public interface Move {
	public boolean checkSpace(int x, int y);
	public boolean checkAbroad(int x,int y);
	public boolean checkObstacle(int x,int y);
	public boolean collisionWithOpponentTank(int x, int y);
	public void tryMoveObject();
	
}
