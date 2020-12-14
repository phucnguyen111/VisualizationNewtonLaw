package hust.soict.globalict.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage){
		try {	
	           Parent root = FXMLLoader.load(getClass().getResource("/hust/soict/globalict/menu/Menu.fxml"));
	           primaryStage.setTitle("Visualization of Newton's Laws of Motion");
	           primaryStage.setResizable(false);
	           Scene scene = new Scene(root);
	           scene.getStylesheets().add(getClass().getResource("/hust/soict/globalict/main/application.css").toExternalForm());
	           primaryStage.setScene(scene);
	           primaryStage.show();	    
	       } catch(Exception e) {
	           e.printStackTrace();
	       }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
