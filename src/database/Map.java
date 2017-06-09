package database;

import java.io.Serializable;

/**
 * @author Patrycja Pawluk, Lukasz Zbrog, Grzegorz Parapura
 * 
 * Klasa odwzorowujaca jedna z tabel bazy
 *
 */
public class Map implements Serializable {
	private int id;
	private int mapNumber;
	private boolean obstacle;
	private int positionX;
	private int positionY;
	
	/**
	 * Metody zwracajace i ustawiajace atrybuty
	 *
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMapNumber() {
		return mapNumber;
	}

	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	
}
