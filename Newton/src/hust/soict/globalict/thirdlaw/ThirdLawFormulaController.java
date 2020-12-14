package hust.soict.globalict.thirdlaw;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.globalict.TheoryController.TheoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * @author Nguyen Thi Hong Anh
 * */
public class ThirdLawFormulaController implements Initializable, TheoryController{
	
	@FXML
	private Button demoButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void openDemo() throws IOException {
		System.out.println("Open third law scenes");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/globalict/thirdlaw/ThirdLawView.fxml"));
		ThirdLawViewController controller = new ThirdLawViewController();
		loader.setController(controller);
		Parent demoPane = loader.load();
		Scene demoScene = new Scene(demoPane);
		Stage stage = (Stage) demoButton.getScene().getWindow();
		Stage newstage = new Stage();
		newstage.initOwner(stage);
		newstage.setScene(demoScene);
		newstage.showAndWait();
	}
}
