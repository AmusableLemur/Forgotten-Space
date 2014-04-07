package main;

import gui.Menu;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import player.Player;

public class Game extends BasicGame {
	private ArrayList<Shape> walls;
	private Menu menu;
	private Player player;
	private State state;

	public Game(String title) {
		super(title);
		
		this.walls = new ArrayList<>();
		this.menu = new Menu(this);
		this.player = new Player();
		this.state = State.MENU;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setBackground(new Color(20, 20, 20));
		g.setColor(new Color(230, 230, 230));
		g.setAntiAlias(true);
		
		switch (state) {
			default:
			case MENU:
				menu.render(gc, g);
				break;
			case PAUSED:
				break;
			case PLAYING:
				player.render(gc, g);
				break;
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		menu.init(gc);
		player.init(gc);
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
				player.update(gc, delta);
				break;
			case QUIT:
				gc.exit();
		}
	}

	public void setState(State state) {
		this.state = state;
	}

}
