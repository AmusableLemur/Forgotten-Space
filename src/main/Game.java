package main;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	private Menu menu;
	private State state;

	public Game(String title) {
		super(title);
		
		this.menu = new Menu(this);
		this.state = State.MENU;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		switch (state) {
			default:
			case MENU:
				menu.render(gc, g);
			case PAUSED:
				break;
			case PLAYING:
				break;
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		menu.init(gc);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		switch (state) {
			default:
			case MENU:
				menu.update(gc, delta);
			case PAUSED:
				break;
			case PLAYING:
				break;
			case QUIT:
				gc.exit();
		}
	}

	public void setState(State state) {
		this.state = state;
	}

}
