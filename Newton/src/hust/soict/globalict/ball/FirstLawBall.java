package hust.soict.globalict.ball;


import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class FirstLawBall extends Ball {

    private RotateTransition ballRotation;

    private TranslateTransition ballTranslate;
    
    private Duration ballMovingTime;
    
    private int force = 0;

	public FirstLawBall(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
		// TODO Auto-generated constructor stub
	}
	
	public int getForce() {
		return this.force;
	}
	public void setForce(int force) {
		this.force = force;
	}
	public RotateTransition getBallRotate() {
		return this.ballRotation;
	}
	public TranslateTransition getBallTranslate() {
		return this.ballTranslate;
	}
	public Duration getBallMovingTime() {
		return this.ballMovingTime;
	}
	public void setBallMovingTime(Duration ballMovingTime) {
		this.ballMovingTime = ballMovingTime;
	}
	public void setBallRotation(RotateTransition ballRotation) {
		this.ballRotation = ballRotation;
	}
	public void setBallTranslate(TranslateTransition ballTranslate) {
		this.ballTranslate = ballTranslate;
	}
	public void pushToTheLeft() {
		if (this.force > -10) {
			// decrease force by 1 while > 1
            this.force--;
        }
        System.out.println("Level of force push to left: " + this.force);
	    
	}
	
	public void pushToTheRight() {
		if (this.force < 10) {
			 //increase force by 1 while < 10
	            this.force++;
	        }
	    System.out.println("Level of force push to right: " + this.force);
		
	}
	
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (this.ballTranslate != null) {
			this.ballTranslate.stop();
			this.ballTranslate.getNode().setTranslateX(0);
			this.ballTranslate = null;
		}
		if (this.ballRotation != null) {
			this.ballRotation.stop();
			this.ballRotation.getNode().setRotate(0);
			this.ballRotation = null;
		}
		
	}

}
