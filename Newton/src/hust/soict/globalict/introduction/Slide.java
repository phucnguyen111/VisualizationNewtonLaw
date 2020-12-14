package hust.soict.globalict.introduction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
 
public class Slide extends AnchorPane implements Initializable {
	
    public Slide(String slide) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(slide));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
