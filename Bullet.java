package logic;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends StackPane {
	private final int TANK_SIZE=50;
	private final int STEP=3;
	
	private int x;
	private int y;
	private String course;
	
	public Bullet(Tank tank, int x,int y, String course) {
		relocate(x+((TANK_SIZE/2)-2), y+((TANK_SIZE/2)-2));
		this.course=course;
		
		this.x=x+((TANK_SIZE/2)-2);
		this.y=y+((TANK_SIZE/2)-2);
		
		Circle cirlce = new Circle(10 * 0.3125);
		cirlce.setFill(Color.valueOf("#FFF111"));

		getChildren().addAll(cirlce);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveBullet() {
		if(course=="UP")
			y-=STEP;
		else if(course=="DOWN")
			y+=STEP;
		else if(course=="RIGHT")
			x+=STEP;
		else if(course=="LEFT")
			x-=STEP;
		relocate(x, y);
	}
}
