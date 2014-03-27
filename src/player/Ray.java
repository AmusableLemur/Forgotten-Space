package player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Ray {
	private Player p;
	
	private int x, y;
	
	public Ray(Player p, int x, int y) {
		this.p = p;
		this.x = x;
		this.y = y;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawLine(p.getX(), p.getY(), x, y);
		g.fillOval(x - 2, y - 2, 4, 4);
	}
}
