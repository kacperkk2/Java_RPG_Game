package characters;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class UserInput {

	public boolean up, down, left, right;
	public boolean stats; // to see player stats
	
	public UserInput(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
		        switch (event.getCode()) {
	                case W: up = true; break;
	                case S: down = true; break;
	                case A: left  = true; break;
	                case D: right = true; break;
	                case C: stats = true; break;
	        	}
	        }
	    });
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	switch (event.getCode()) {
	                case W: up = false; break;
	                case S: down = false; break;
	                case A: left  = false; break;
	                case D: right = false; break;
	                case C: stats = false; break;
            	}
            }
        });
	}
}
