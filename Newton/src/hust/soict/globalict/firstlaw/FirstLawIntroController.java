package hust.soict.globalict.firstlaw;

import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.globalict.TheoryController.TheoryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FirstLawIntroController implements Initializable, TheoryController{
	@FXML
	private Button demoButton;
	
	@Override
	public  void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void openDemo() {
		
		Stage stage = new Stage();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hust/soict/globalict/firstlaw/FirstLawImplement.fxml"));
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Newton's First Law Demo");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
