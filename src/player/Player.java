package player;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

public class Player implements InputProviderListener {
	private InputProvider provider;

	private Command commandUp = new BasicCommand("up");
	private Command commandLeft = new BasicCommand("left");
	private Command commandDown = new BasicCommand("down");
	private Command commandRight = new BasicCommand("right");
	private Command commandShoot = new BasicCommand("shoot");
	
	private ArrayList<Ray> rays;
	
	private boolean up, down, left, right;

	private float x, y;
	private float speed;
	
	public Player() {
		rays = new ArrayList<Ray>();
		speed = 2;
	}
	
	public void init(GameContainer gc) throws SlickException {
		provider = new InputProvider(gc.getInput());

		provider.addListener(this);
		provider.bindCommand(new KeyControl(Input.KEY_UP), commandUp);
		provider.bindCommand(new KeyControl(Input.KEY_W), commandUp);
		provider.bindCommand(new KeyControl(Input.KEY_LEFT), commandLeft);
		provider.bindCommand(new KeyControl(Input.KEY_A), commandLeft);
		provider.bindCommand(new KeyControl(Input.KEY_DOWN), commandDown);
		provider.bindCommand(new KeyControl(Input.KEY_S), commandDown);
		provider.bindCommand(new KeyControl(Input.KEY_RIGHT), commandRight);
		provider.bindCommand(new KeyControl(Input.KEY_D), commandRight);
		provider.bindCommand(new KeyControl(Input.KEY_SPACE), commandShoot);
		
		rays.add(new Ray(this, 0, 0));
		rays.add(new Ray(this, gc.getWidth(), 0));
		rays.add(new Ray(this, 0, gc.getHeight()));
		rays.add(new Ray(this, gc.getWidth(), gc.getHeight()));
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Ray r : rays) {
			r.render(gc, g);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		if (up) {
			y -= speed;
		}
		
		if (left) {
			x -= speed;
		}
		
		if (down) {
			y += speed;
		}
		
		if (right) {
			x += speed;
		}
	}

	@Override
	public void controlPressed(Command command) {
		if (command.equals(commandUp)) {
			up = true;
		}
		
		if (command.equals(commandLeft)) {
			left = true;
		}
		
		if (command.equals(commandDown)) {
			down = true;
		}
		
		if (command.equals(commandRight)) {
			right = true;
		}
	}

	@Override
	public void controlReleased(Command command) {
		if (command.equals(commandUp)) {
			up = false;
		}
		
		if (command.equals(commandLeft)) {
			left = false;
		}
		
		if (command.equals(commandDown)) {
			down = false;
		}
		
		if (command.equals(commandRight)) {
			right = false;
		}
	}
}
