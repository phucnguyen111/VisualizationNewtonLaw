package hust.soict.globalict.TheoryController;

import java.io.IOException;

/**
 * @author Nguyen Thi Hong Anh
 * @apiNote
 * This is a functional interface
 * Any class that inherits it means that class can open new demo window 
 * 
 * */

public interface TheoryController {
	public void openDemo() throws IOException;
}
