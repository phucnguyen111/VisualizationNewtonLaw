package hust.soict.globalict.secondlaw;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.globalict.ball.Ball;
import hust.soict.globalict.ball.SecondLawBall;
import hust.soict.globalict.kickable.Kickable;
import hust.soict.globalict.resetable.Resetable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;

/**
 * 
 * @author Hong Anh
 * 
 *         Controller for SecondLawView.fxml
 * 
 *         CURRENT BUGS: + When distance travelled + initial position < pand
 *         width, after the ball finish translating it will rotate in place +
 *         When distance traveleed >> rotate rate is veri small --> looks stupid
 */

public class SecondLawViewController implements Initializable, Kickable, Resetable {

	
	private final double gravitationalAcceleration = 9.8;

	@FXML
	private AnchorPane pane;
	
	@FXML
	private ImageView frictionPokemonImage;

	@FXML
	private ImageView frictionBubbleChatImage;

	@FXML
	private Label frictionLabel;
	@FXML
	private Button kickButton;

	@FXML
	private Button resetButton;

	@FXML
	private TextField frictionalCoefTextField;

	@FXML
	private TextField initialVelocityTextField;

	@FXML
	private TextField accelerationTextField;

	@FXML
	private TextField distanceTextField;

	@FXML
	private Ball ball;

	@FXML
	private Label statusLabel;

	public SecondLawViewController() {
		// pathTransition = null;
		ball = new SecondLawBall(262, 534, 86);
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		Image pokeball = new Image("/hust/soict/globalict/images/pokeball.png");
		ball.setFill(new ImagePattern(pokeball));
		accelerationTextField.setEditable(false);
		pane.getChildren().add(ball);

		/*
		 * Prevent user from making initial velocity too bid
		 */
		initialVelocityTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldvalue, String newvalue) {
				// TODO Auto-generated method stub
				/* This forces user to enter integers
				if (!newvalue.matches("\\d*")) {
					initialVelocityTextField.setText(newvalue.replaceAll("[^\\d]", ""));
				}*/
				if (!newvalue.equals("") && Double.parseDouble(newvalue) > 30) {
					newvalue = "30";
				}
				initialVelocityTextField.setText(newvalue);

			}
		});

		frictionalCoefTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldvalue, String newvalue) {
				// TODO Auto-generated method stub
				/* This forces user to enter integers
				if (!newvalue.matches("\\d*")) {
					initialVelocityTextField.setText(newvalue.replaceAll("[^\\d]", ""));
				}*/
				if (!newvalue.equals("") && Double.parseDouble(newvalue) > 1) {
					
					newvalue = "1";
					frictionalCoefTextField.setText(newvalue);
				}
				

			}
		});
	}

	@FXML
	public void kick(ActionEvent event) {
		kickButton.setDisable(true);
		String frictionString = frictionalCoefTextField.getText().trim();
		String initialVelocityString = initialVelocityTextField.getText().trim();
		if ((!frictionString.equals("")) && (!initialVelocityString.equals(""))) {
			double friction = Double.parseDouble(frictionString);
			double initialVelocity = Double.parseDouble(initialVelocityString);

			double acceleration = -friction * gravitationalAcceleration;
			double distance = (initialVelocity * initialVelocity) / (-2 * acceleration);

			accelerationTextField.setText(String.format("%.3f", acceleration));
			distanceTextField.setText(String.format("%.3f", distance));

			double time = calculateTime(distance, initialVelocity, acceleration);
			
			ball.setTimeTo(time);
			ball.setDistanceTo(distance);
			ball.roll();
		}
	}

	@FXML
	public void reset(ActionEvent event) {
		System.out.println("Reset is pressed");

		kickButton.setDisable(false);
		ball.reset();
		
		frictionalCoefTextField.setText("");
		initialVelocityTextField.setText("");
		distanceTextField.setText("");
		accelerationTextField.setText("");

	}

	// a * t * t + v0 * t - distance = 0
	public double calculateTime(double distance, double velocity, double acceleration) {
		double time = 0;
		double delta = velocity * velocity - 4 * acceleration * distance;
		if (delta < 0) {
			time = 0;
		} else if (delta == 0) {
			time = (-velocity) / (2 * acceleration);
		} else {
			double t1 = (-velocity + Math.sqrt(delta)) / (2 * acceleration);
			double t2 = (-velocity - Math.sqrt(delta)) / (2 * acceleration);
			if (t1 > 0 && t2 < 0) {
				time = t1;
			} else if (t1 < 0 && t2 > 0) {
				time = t2;
			} else if (t1 > 0 && t2 > 0) {
				time = t1 > t2 ? t1 : t2;
			}
		}
		return time;
	}
}
