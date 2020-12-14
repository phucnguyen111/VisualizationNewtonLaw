package hust.soict.globalict.thirdlaw;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.globalict.ball.Ball;
import hust.soict.globalict.ball.ThirdLawBall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Nguyen Thi Hong Anh
 * */
public class ThirdLawViewController implements Initializable {

	// Initial coordinates of the ball
	private double initialXBall;
	private double ballRadius;

	// x coordinate of the wall
	private double xWall;
	private double widthWall;
	private final double afterVelocity = 15;
	private final double touchTime = 0.5;
	
	

	@FXML
	private AnchorPane pane;
	
	private Ball ball;

	@FXML
	private Button kickButton;

	@FXML
	private Button resetButton;

	@FXML
	private TextField weightTextField;

	@FXML
	private TextField initialVelocityTextField;

	@FXML
	private TextField forceTextField;

	@FXML
	private TextField accelerationTextField;

	@FXML
	private Rectangle wall;


	public ThirdLawViewController() {
		ball = new ThirdLawBall(181, 579, 82);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image pokeball = new Image("/hust/soict/globalict/images/pokeball.png");
		ball.setFill(new ImagePattern(pokeball));
		initialXBall = ball.getCenterX();
		ballRadius = ball.getRadius();
		
		pane.getChildren().add(ball);
		xWall = wall.getLayoutX();
		widthWall = wall.getWidth();
		
		accelerationTextField.setEditable(false);
		forceTextField.setEditable(false);
		

		/*
		initialVelocityTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldvalue, String newvalue) {
				// TODO Auto-generated method stub

				if (!newvalue.matches("\\d*")) {
					initialVelocityTextField.setText(newvalue.replaceAll("[^\\d]", ""));
				}
				if (!newvalue.equals("") && Double.parseDouble(newvalue) <= 15) {
					newvalue = "16";
				}
				initialVelocityTextField.setText(newvalue);

			}
		});*/
	}

	/**
	 * When this function is called, it will make the ball move according to the
	 * parameters set by the user
	 * 
	 */
	public void kick(ActionEvent event) {
		kickButton.setDisable(true);
		double initialVelocity = Double.parseDouble(initialVelocityTextField.getText());

		double acceleration = (initialVelocity - afterVelocity) / touchTime;
		accelerationTextField.setText(String.format("%.2f", acceleration));

		double ballWeight = Double.parseDouble(weightTextField.getText());
		double force = ballWeight * acceleration;
		forceTextField.setText(String.format("%.2f", force));

		//double distanceToWall = xWall - 3*ballRadius - initialXBall - widthWall;
		double distanceToWall = xWall - 3*ballRadius - initialXBall;
		System.out.println("xWall: " + xWall);
		System.out.println("Ball radius: " + ballRadius);
		System.out.println("InitialXBall: " + initialXBall);
		System.out.println("widthWall: " + widthWall);
		System.out.println("Distance: " + distanceToWall);
		double timeRollToWall = distanceToWall/ initialVelocity;
		System.out.println();
		
		double ratio = Math.abs((xWall - initialXBall + 2*ballRadius + 250)/distanceToWall);
		System.out.println("Ratio: " + ratio);
		
		/*
		 * This part is animation of ball rolling to wall
		 * */
		ball.setRatio(ratio);
		ball.setTimeTo(timeRollToWall);
		ball.setDistanceTo(distanceToWall);
		ball.roll();
	}

	/**
	 * When this function is called, it will reset all components back to their
	 * initial status ball: inital coordinates Textfields are cleared
	 * 
	 */
	public void reset(ActionEvent event) {
		System.out.println("Reset is pressed");
		/*
		 * if(pathTransition != null) { pathTransition.stop(); }
		 */
		ball.reset();
		kickButton.setDisable(false);
		accelerationTextField.setText("");
		initialVelocityTextField.setText("");
		forceTextField.setText("");
		weightTextField.setText("");

	}
}
