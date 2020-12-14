package hust.soict.globalict.ball;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class ThirdLawBall extends Ball{
	
	private RotateTransition rotateToWall;
	private TranslateTransition translateToWall;
	private RotateTransition rotateFromWall;
	private TranslateTransition translateFromWall;
	
	Timeline timeline;
	public ThirdLawBall(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
		rotateToWall = null;
		translateToWall = null;
		rotateFromWall = null;
		translateFromWall = null;
		timeline = null;
	}

	@Override
	public void roll() {
		
		translateToWall = new TranslateTransition(Duration.millis(timeTo*100), this);
		translateToWall.setInterpolator(Interpolator.LINEAR);
		translateToWall.setToX(initialXBall + distanceTo);
		
		rotateToWall = new RotateTransition(Duration.millis(timeTo*100), this);
		rotateToWall.setInterpolator(Interpolator.LINEAR);
		rotateToWall.setCycleCount(Animation.INDEFINITE);
		rotateToWall.setByAngle(360);
		
		translateToWall.setOnFinished(actionEvent -> {
			rotateToWall.stop();
			rotateToWall.getNode().setRotate(0);
			rotateToWall = null;
		});
		/*
		 * This part is animation of ball rolling from wall
		 * */
		
		translateFromWall = new TranslateTransition(Duration.millis(timeTo*100*ratio), this);
		translateFromWall.setInterpolator(Interpolator.LINEAR);
		translateFromWall.setToX(initialXBall-2*this.getRadius()-250);
		
		rotateFromWall = new RotateTransition(Duration.millis(timeTo*100*ratio), this);
		rotateFromWall.setInterpolator(Interpolator.LINEAR);
		rotateFromWall.setCycleCount(Timeline.INDEFINITE);
		rotateFromWall.setByAngle(-360);
		
		translateFromWall.setOnFinished(actionEvent -> {
			rotateFromWall.stop();
			rotateFromWall.getNode().setRotate(0);
			rotateFromWall = null;
		});
		/*
		 * Time in the constructor of the Keyframe means the offset time. The time at which to start the keyframe
		 * */
		KeyFrame kfBallToWall = new KeyFrame(Duration.ZERO, e -> {
			translateToWall.playFromStart();
			rotateToWall.playFromStart();
		});
		
		KeyFrame kfBallFromWall = new KeyFrame(Duration.millis(timeTo*100), e -> {
			//rotateToWall.stop();
			//translateToWall.stop();
			translateFromWall.playFromStart();
			rotateFromWall.playFromStart();
		});
		
		
		timeline = new Timeline(kfBallToWall, kfBallFromWall);
		//timeline.setDelay(Duration.millis(0));

		
		timeline.playFromStart();
	}

	@Override
	public void reset() {
		if (timeline != null) {
			timeline.stop();
			
			translateToWall.stop();
			translateFromWall.stop();
			if(rotateToWall != null) {
				rotateToWall.stop();
				rotateToWall.getNode().setRotate(0);
				rotateToWall = null;
			}
			if(rotateFromWall != null) {
				rotateFromWall.stop();
				rotateFromWall.getNode().setRotate(0);
				rotateFromWall = null;
			}
			translateToWall = null;
			rotateToWall = null;
			translateFromWall = null;
			rotateFromWall = null;
			
			timeline = null;
		}
		
		
		this.setLayoutX(this.initialXBall);
		this.setLayoutY(this.initialYBall);
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.setRotate(0);
	}

}
