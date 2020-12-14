package hust.soict.globalict.ball;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SecondLawBall extends Ball {
	TranslateTransition translate;
	RotateTransition rotate;
	
	public SecondLawBall(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
		rotate = null;
		translate = null;
	}
	
	public void setRotation(RotateTransition rotate) {
		this.rotate = rotate;
	}

	public void setTransition(TranslateTransition translate) {
		this.translate = translate;
	}
	
	
	@Override
	public void roll() {
		
		translate = new TranslateTransition(Duration.seconds(timeTo), this);
		translate.setInterpolator(Interpolator.EASE_BOTH);
		translate.setToX(initialXBall + distanceTo * 30);

		rotate = new RotateTransition(Duration.seconds(timeTo), this);
		rotate.setInterpolator(Interpolator.EASE_BOTH);
		rotate.setCycleCount(Animation.INDEFINITE);
		;
		rotate.setByAngle(360);

		/*
		 * This is utility to help the ball stop rotating when translating is done
		 * 
		 */
		translate.setOnFinished(actionEvent -> {
			rotate.stop();
			rotate.getNode().setRotate(0);
		});
		rotate.play();
		translate.play();

	}

	@Override
	public void reset() {
		
		if (this.rotate != null) {
			// this.timeline.stop();

			this.rotate.stop();
			this.rotate.getNode().setRotate(0);
			this.rotate = null;

			this.translate.stop();
			this.translate.getNode().setTranslateX(0);
			this.translate = null;

			// this.timeline = null;
		}
		
		this.setCenterX(this.initialXBall);
		this.setCenterY(this.initialYBall);
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.setRotate(0);

	}


}
