package hust.soict.globalict.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.globalict.TheoryController.TheoryController;
import hust.soict.globalict.introduction.Presentation;
import hust.soict.globalict.introduction.Slide;
import hust.soict.globalict.secondlaw.SecondLawFormulaController;
import hust.soict.globalict.thirdlaw.ThirdLawFormulaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	/**
	 * @author: Nguyen Manh Phuc
	 * */
	@FXML
	private Button introButton;
	@FXML
	private Button firstLawButton;
	@FXML
	private Button secondLawButton;
	@FXML
	private Button thirdLawButton;
	
	@Override
	public  void initialize(URL location, ResourceBundle resources) {
		
	}
	public void showIntroduction() {
		Stage stage = new Stage();
		Presentation presentation = new Presentation();
        presentation.addSlide(new Slide("Slide1.fxml"));
        presentation.addSlide(new Slide("Slide2.fxml"));
        presentation.addSlide(new Slide("Slide3.fxml"));
        presentation.addSlide(new Slide("Slide4.fxml"));
        Scene scene = new Scene(presentation);
        stage.setScene(scene);
        stage.setFullScreen(true);
        presentation.start();
        
        stage.show();
	}
	
	public void showFirstLaw() {
		
		Stage stage = new Stage();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hust/soict/globalict/firstlaw/FirstLawIntro.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Newton's First Law");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	/**
	 * @author Nguyen Thi Hong Anh
	 * */
	@FXML
	public void showSecondLaw(ActionEvent e) throws IOException {
		System.out.println("Open second law scenes");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/globalict/secondlaw/SecondLawFormulaView.fxml"));
		TheoryController theoryController = new SecondLawFormulaController();
		loader.setController(theoryController);
		Parent introductionPane = loader.load();
		Scene introductionScene = new Scene(introductionPane);
		Stage stage = (Stage) introButton.getScene().getWindow();
		Stage newstage = new Stage();
		newstage.initOwner(stage);
		newstage.setScene(introductionScene);
		newstage.showAndWait();
	}
	
	@FXML
	public void showThirdLaw(ActionEvent e) throws IOException {
		System.out.println("Open third law scenes");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/globalict/thirdlaw/ThirdLawFormulaView.fxml"));
		TheoryController theoryController = new ThirdLawFormulaController();
		loader.setController(theoryController);
		Parent introductionPane = loader.load();
		Scene introductionScene = new Scene(introductionPane);
		Stage stage = (Stage) introButton.getScene().getWindow();
		Stage newstage = new Stage();
		newstage.initOwner(stage);
		newstage.setScene(introductionScene);
		newstage.showAndWait();
	}
}
