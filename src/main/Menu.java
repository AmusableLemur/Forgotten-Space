package main;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

public class Menu implements InputProviderListener {
	private static final String[] options = {"Play", "Settings", "Quit"};
	
	private int choice;
	
	private TrueTypeFont titleFont;
	
	private Command up = new BasicCommand("up");
	private Command down = new BasicCommand("down");
	private Command select = new BasicCommand("select");
	
	private InputProvider provider;
	
	private Game game;
	
	public Menu(Game g) {
		this.game = g;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(titleFont);
		g.drawString(game.getTitle(), 100, 100);
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
		
		provider = new InputProvider(gc.getInput());

		provider.addListener(this);
		provider.bindCommand(new KeyControl(Input.KEY_UP), up);
		provider.bindCommand(new KeyControl(Input.KEY_W), up);
		provider.bindCommand(new KeyControl(Input.KEY_DOWN), down);
		provider.bindCommand(new KeyControl(Input.KEY_S), down);
		provider.bindCommand(new KeyControl(Input.KEY_ENTER), select);
		provider.bindCommand(new KeyControl(Input.KEY_SPACE), select);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		
	}

	@Override
	public void controlPressed(Command c) {
		if (c.equals(up)) {
			choice -= 1;
			
			if (choice < 0) {
				choice = 0;
			}
		}
		
		if (c.equals(down)) {
			choice += 1;
			
			if (choice >= options.length) {
				choice = options.length - 1;
			}
		}
		
		if (c.equals(select)) {
			if (choice == 0) {
				game.setState(State.PLAYING);
			}
			else if (choice == 2) {
				game.setState(State.QUIT);
			}
		}
	}

	@Override
	public void controlReleased(Command c) {
		//System.out.println(c.toString());
	}
}
