package logic;

public interface Move {
	public boolean checkSpace(int x, int y);
	public boolean checkAbroad(int x,int y);
	public boolean checkObstacle(int x,int y);
	public boolean collisionWithOpponentTank(int x, int y);
	public void tryMoveObject();
	
	public default int toBoard(int x) {
		int returnX = ((x-1) / 100);
		if(returnX==-1)
			return 0;
		return returnX;
	}
	
}
