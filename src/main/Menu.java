package main;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Menu {
	private static final String[] options = {"Play", "Settings", "Quit"};
	
	private int choice;
	
	private TrueTypeFont titleFont;
	
	public Menu() {
		
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(titleFont);
		g.drawString("Forgotten Space", 100, 100);
		g.resetFont();
		
		for (int i = 0; i < options.length; i++) {
			if (i == choice) {
				g.setColor(Color.darkGray);
				g.fillRect(90, 150 + i * 20, 100, 20);
				g.setColor(Color.white);
			}
			
			g.drawString(options[i], 100, 150 + i * 20);
		}
	}

	public void init(GameContainer gc) throws SlickException {
		Font font;
		
		font = new Font("Courier New", Font.BOLD, 24);
		titleFont = new TrueTypeFont(font, true);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
