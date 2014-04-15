package gui;

import java.awt.Font;
import java.util.ArrayList;

import main.Game;
import main.State;

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
import org.newdawn.slick.command.MouseButtonControl;

public class Menu implements InputProviderListener {
	private static final int buttonHeight = 20;
	private static final int buttonWidth = 100;
	private static final int buttonsX = 100;
	private static final int buttonsY = 150;
	
	private int choice;
	
	private ArrayList<Button> buttons;
	
	private Command up = new BasicCommand("up");
	private Command down = new BasicCommand("down");
	private Command click = new BasicCommand("select");
	
	private Game game;
	
	private InputProvider provider;
	
	private TrueTypeFont titleFont;
	
	public Menu(Game g) {
		this.buttons = new ArrayList<Button>();
		this.game = g;
		
		buttons.add(new Button("Play"));
		buttons.add(new Button("Settings"));
		buttons.add(new Button("Quit"));
	}
	
	private boolean isMouseOnButton(int x, int y, int mouseX, int mouseY) {
		if (mouseX < x - 10) {
			// Mouse is left of button
			return false;
		}
		
		if (mouseX > x + buttonWidth - 10) {
			// Mouse is right of button
			return false;
		}
		
		if (mouseY < y) {
			// Mouse is above button
			return false;
		}
		
		if (mouseY > (y + buttonHeight)) {
			// Mouse is below button
			return false;
		}
		
		return true;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setFont(titleFont);
		g.drawString(game.getTitle(), 100, 100);
		g.resetFont();
		
		for (int i = 0; i < buttons.size(); i++) {
			int x = buttonsX;
			int y = buttonsY + i * buttonHeight;
			
			if (i == choice) {
				g.setColor(Color.darkGray);
				g.fillRect(x - 10, y, buttonWidth, buttonHeight);
				g.setColor(Color.white);
			}
			
			g.drawString(buttons.get(i).getLabel(), x, y);
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
		provider.bindCommand(new KeyControl(Input.KEY_ENTER), click);
		provider.bindCommand(new KeyControl(Input.KEY_SPACE), click);
		provider.bindCommand(new MouseButtonControl(Input.MOUSE_LEFT_BUTTON), click);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		
		for (int i = 0; i < buttons.size(); i++) {
			int x = buttonsX;
			int y = buttonsY + i * buttonHeight;
			
			if (isMouseOnButton(x, y, input.getMouseX(), input.getMouseY())) {
				choice = i;
			}
		}
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
			
			if (choice >= buttons.size()) {
				choice = buttons.size() - 1;
			}
		}
		
		if (c.equals(click)) {
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
