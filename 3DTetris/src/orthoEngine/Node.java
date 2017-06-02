package orthoEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Node extends ZGetter{
	
	public double x, y, z;
	public int sz;
	public Color c;
	
	public Node(double x,double y,double z, int sz, Color c) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sz = sz;
		this.c = c;
		
	}
	public Node(double x,double y,double z, int sz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sz = sz;
		this.c = Color.WHITE;
		
	}
	public double averageZ() {
		return z;
	}
	void draw(Graphics2D g) {
		g.setColor(c);
		g.fillOval((int) x - sz/2,(int) y - sz/2,sz, sz);
	}
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval((int) x - sz/2,(int) y - sz/2,sz, sz);
	}
	@Override
	void setColor(Color c) {
		this.c = c;
		
	}
}
