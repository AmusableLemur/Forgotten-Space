package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Game("Forgotten Space"));
			app.setDisplayMode(640, 480, false);
			app.setTargetFrameRate(60);
			app.start();
		}
		catch (SlickException e) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
