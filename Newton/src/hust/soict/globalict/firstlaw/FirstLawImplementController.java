package hust.soict.globalict.firstlaw;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.globalict.ball.FirstLawBall;
import hust.soict.globalict.resetable.Resetable;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class FirstLawImplementController implements Initializable, Resetable {
	
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Button pushToTheLeftButton;
	@FXML
	private Button pushToTheRightButton;
	@FXML
	private Button resetButton;
	
	@FXML
	private Pane pikachuPane;
	@FXML
	private Pane snorlaxPane;
	@FXML
	private Label forceLabel;
	private FirstLawBall firstlawball;
	
	
	public FirstLawImplementController() {
		firstlawball = new FirstLawBall(0,0,100);
		firstlawball.setLayoutX(738);
		firstlawball.setLayoutY(470);
	}
	
	@Override
	public  void initialize(URL location, ResourceBundle resources) {
		// set pokeball image to circle
		Image pokeball = new Image("/hust/soict/globalict/images/pokeball.png");
		firstlawball.setFill(new ImagePattern(pokeball));
		pikachuPane.setVisible(false);
		snorlaxPane.setVisible(true);
		mainPane.getChildren().add(firstlawball);
		
	}
	// pushToTheRight Button control
	public void pushToTheRight() {
		firstlawball.pushToTheRight();
	    setRolling();
	}
	// pushToTheLeft Button control
	public void pushToTheLeft() {
		firstlawball.pushToTheLeft();
        setRolling();
	}
	
	
	
	private void setRolling() {
		if(firstlawball.getForce() == 0) {
			//if force equals zero set 2 pane visibility
			pikachuPane.setVisible(false);
        	snorlaxPane.setVisible(true);
        	
		}
		
		if (firstlawball.getBallRotate() != null) {
			//stop to pickup new value
            firstlawball.getBallRotate().stop();
		}
		if (firstlawball.getBallTranslate() != null) {
			System.out.println("Status: " + firstlawball.getBallTranslate().getStatus().name());
			// when ball state is stopped 
            if(firstlawball.getBallTranslate().getStatus().equals(Animation.Status.STOPPED)){
				// if ball angle * force > 0 meaning it still go the same direction set new ballMovingTime to time of previous state
				if(firstlawball.getBallRotate().getByAngle() * firstlawball.getForce() > 0){
					firstlawball.setBallMovingTime(firstlawball.getBallMovingTime().add(Duration.millis(0)));
					//ballMovingTime = ballMovingTime.add(Duration.millis(0));
				}
				else{
					//else go opposite direction set ballMovingtime to be 1-previous state time
					firstlawball.setBallMovingTime(Duration.millis(1).subtract(firstlawball.getBallMovingTime()));
					//ballMovingTime = Duration.millis(1).subtract(ballMovingTime);
				}

			}
			// else ball state is running 
			else{
				firstlawball.setBallMovingTime(firstlawball.getBallTranslate().getCurrentTime().divide(firstlawball.getBallTranslate().getDuration().toMillis()));
				//ballMovingTime = this.ballTranslate.getCurrentTime().divide(this.ballTranslate.getDuration().toMillis());
			}
			System.out.println("Current translate time: "+firstlawball.getBallTranslate().getCurrentTime().toMillis());
            System.out.println("Duration translate previous state: " + firstlawball.getBallTranslate().getDuration().toMillis());
            System.out.println("Angle: "+firstlawball.getBallRotate().getByAngle());
			System.out.println("Current ball moving time translate: " + firstlawball.getBallMovingTime());
		
			//stop to pickup new value 
			firstlawball.getBallTranslate().stop();
        }
		// when ballRotate and ballTranslate = null run here to initialize first
		if(firstlawball.getForce()!=0) {
			// pikachu pane label show force level
			pikachuPane.setVisible(true);
			forceLabel.setText("Force Level : "+Math.abs(firstlawball.getForce())+"/10");
        	snorlaxPane.setVisible(false);
        	
        	// initialize rotate with duration = 6s
			RotateTransition rotate = new RotateTransition(Duration.millis(6000/Math.abs(firstlawball.getForce())),firstlawball);
			//set ball rotation angle to be 360 when force > 0 and -360 otherwise
			if(firstlawball.getForce() > 0){
				rotate.setByAngle(360);
			}
			else{
				rotate.setByAngle(-360);
			}
    		// rotate cycle to repeat indefinitely 
			rotate.setCycleCount(Animation.INDEFINITE);
			// rotate interpolator to be linear time
			rotate.setInterpolator(Interpolator.LINEAR);
			// play
    	    rotate.play();
    	    firstlawball.setBallRotation(rotate);
    	    
    	    // initialize translate with duration = 5s
			TranslateTransition translate = new TranslateTransition(Duration.millis(6000/Math.abs(firstlawball.getForce())),firstlawball);
			// translate cycle repeat indefinitely
			translate.setCycleCount(Animation.INDEFINITE);
			// translate interpolator to be linear time
    	    translate.setInterpolator(Interpolator.LINEAR);
    	    
    	    if (firstlawball.getForce() > 0) {
				// use to get pokeball back to the opposite side of scene when reach the end of the other side of scene
                translate.setFromX(mainPane.getLayoutBounds().getMaxX()/-1.8);
                translate.setToX(mainPane.getLayoutBounds().getMaxX()/1.8);
            } else {
                translate.setFromX(mainPane.getLayoutBounds().getMaxX()/1.8);
                translate.setToX(mainPane.getLayoutBounds().getMaxX()/-1.8);
            }
    	    
    	    // set playFrom a  position 
    	    Double duration = translate.getDuration().toMillis();
			System.out.println("Duration now: "+ duration);
			if(firstlawball.getBallMovingTime() != null){
				// if != null play from ballMovingtime * current translate duration
				translate.playFrom(firstlawball.getBallMovingTime().multiply(duration));
				System.out.println("Play from: "+ firstlawball.getBallMovingTime().multiply(duration));
			}
			else{
				// if null playFrom middle
				translate.playFrom(Duration.millis(3000));
			}
    	    
			firstlawball.setBallTranslate(translate);
		}
		
	}
	//reset button handle

	public void reset(ActionEvent event) {
		firstlawball.setForce(0);
		pikachuPane.setVisible(false);
		snorlaxPane.setVisible(true);
		firstlawball.setBallMovingTime(null);
		firstlawball.reset();
	}
}
