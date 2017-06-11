package logic;

import gui.GameContainer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tank extends StackPane {
	public final int TANK_SIZE = 50;

	private Image image;
	private Rectangle rectangle;
	private GameContainer gameContainer;

	private String color;
	private String course;
	private int tankHealth;
	private int x;
	private int y;
	
	public Tank(int startX, int startY, String color, String course,GameContainer gameContainer) {
		relocate(startX, startY);
		this.x = startX;
		this.y = startY;

		this.color = color;
		this.course=course;
		this.gameContainer=gameContainer;
		tankHealth=4;

		image = new Image(getClass().getResourceAsStream("/" + color + course+".gif"));

		rectangle = new Rectangle(TANK_SIZE, TANK_SIZE);

		rectangle.setFill(new ImagePattern(image));
		getChildren().addAll(rectangle);
	}
	
	public void setTankHealth(int tankHealth){
		this.tankHealth=tankHealth;
	}
	
	public int getTankHealth(){
		return tankHealth;
	}
	
	public void tankHit(int numberPlayer){
		tankHealth--;
		gameContainer.updateHpRectangle(tankHealth,numberPlayer);
		if(tankHealth==0){
			gameContainer.removeTank(this);
		}
	}

	public void setRectangleNull() {
		rectangle = null;
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized int getY() {
		return y;
	}
	
	public synchronized void moveTank(int newX, int newY) {
			this.x = newX;
			this.y = newY;	
		relocate(this.x, this.y);
	}

	public void setCourse(KeyCode keycode) {
		course = keycode.toString();
	}

	public String getCourse() {
		return course;
	}

	public void updateTankImage(KeyCode keycode) {
		String nameImage = color + keycode.toString();
		image = new Image(getClass().getResourceAsStream("/" + nameImage + ".gif"));
		rectangle.setFill(new ImagePattern(image));
	};
}
