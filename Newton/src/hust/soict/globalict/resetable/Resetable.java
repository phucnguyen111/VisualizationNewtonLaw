package hust.soict.globalict.resetable;

import javafx.event.ActionEvent;

/**
 * @author Nguyen Thi Hong Anh
 * @apiNote
 * This is a functional interface
 * Any class that inherits it means that class has reset behaviour
 * */

public interface Resetable {
	public void reset(ActionEvent event);
}
