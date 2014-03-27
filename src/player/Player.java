package player;

import java.awt.Font;

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

public class Player implements InputProviderListener {
	private InputProvider provider;

	private Command up = new BasicCommand("up");
	private Command left = new BasicCommand("left");
	private Command down = new BasicCommand("down");
	private Command right = new BasicCommand("right");
	private Command shoot = new BasicCommand("shoot");
	
	private Direction horizontal = Direction.NONE;
	private Direction vertical = Direction.NONE;

	private double x, y;
	private double speed;
	
	public Player() {
		speed = 2;
	}
	
	public void init(GameContainer gc) throws SlickException {
		provider = new InputProvider(gc.getInput());

		provider.addListener(this);
		provider.bindCommand(new KeyControl(Input.KEY_UP), up);
		provider.bindCommand(new KeyControl(Input.KEY_W), up);
		provider.bindCommand(new KeyControl(Input.KEY_LEFT), left);
		provider.bindCommand(new KeyControl(Input.KEY_A), left);
		provider.bindCommand(new KeyControl(Input.KEY_DOWN), down);
		provider.bindCommand(new KeyControl(Input.KEY_S), down);
		provider.bindCommand(new KeyControl(Input.KEY_RIGHT), right);
		provider.bindCommand(new KeyControl(Input.KEY_D), right);
		provider.bindCommand(new KeyControl(Input.KEY_SPACE), shoot);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.fillOval((int)x, (int)y, 10, 10);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		if (horizontal.equals(Direction.FORWARD)) {
			x += speed;
		}
		
		if (horizontal.equals(Direction.BACKWARD)) {
			x -= speed;
		}
		
		if (vertical.equals(Direction.FORWARD)) {
			y += speed;
		}
		
		if (vertical.equals(Direction.BACKWARD)) {
			y -= speed;
		}
	}

	@Override
	public void controlPressed(Command command) {
		if (command.equals(up)) {
			vertical = Direction.BACKWARD;
		}
		else if (command.equals(down)) {
			vertical = Direction.FORWARD;
		}
		
		if (command.equals(left)) {
			horizontal = Direction.BACKWARD;
		}
		else if (command.equals(right)) {
			horizontal = Direction.FORWARD;
		}
	}

	@Override
	public void controlReleased(Command command) {
		if (command.equals(up) || command.equals(down)) {
			vertical = Direction.NONE;
		}
		
		if (command.equals(left) || command.equals(right)) {
			horizontal = Direction.NONE;
		}
	}
}
