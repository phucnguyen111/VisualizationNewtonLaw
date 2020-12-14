package hust.soict.globalict.kickable;

import javafx.event.ActionEvent;

/**
 * @author Nguyen Thi Hong Anh
 * @apiNote
 * This is a functional interface
 * Any class that inherits it means that class has kick behaviour
 * */
public interface Kickable {
	public void kick(ActionEvent event);
}
