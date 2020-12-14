package hust.soict.globalict.ball;

import javafx.scene.shape.Circle;

public abstract class Ball extends Circle {
	
	double initialXBall;
	double initialYBall;
	double timeFrom;
	double timeTo;
	double distanceFrom;
	double distanceTo;
	double ratio ;
	public Ball(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
		this.initialXBall = centerX;
		this.initialYBall = centerY;
	}
	
	public double getInitialXBall() {
		return initialXBall;
	}
	
	public double getInitialYBall() {
		return initialYBall;
	}

	public void roll() {} ;
	
	public abstract void reset();
	
	public void setTimeFrom(double time) {
		this.timeFrom = time;
	}
	public void setDistanceFrom(double distance) {
		this.distanceFrom = distance;
	}
	public void setTimeTo(double time) {
		this.timeTo = time;
	}
	public void setDistanceTo(double distance) {
		this.distanceTo = distance;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
