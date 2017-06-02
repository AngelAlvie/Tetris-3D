package orthoEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class ZGetter {
	public abstract double averageZ();
	abstract void draw(Graphics2D g);
	public abstract void draw(Graphics g);
	abstract void setColor(Color c);
}
