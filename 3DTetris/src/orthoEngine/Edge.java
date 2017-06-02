package orthoEngine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Edge extends ZGetter{
	
	Node n1, n2;
	int sz;
	Color c;
	public Edge(Node n1, Node n2, int sz, Color c) {
		this.n1 = n1;
		this.n2 = n2;
		this.sz = sz;
		this.c = c;
	}
	public Edge(Node n1, Node n2, int sz) {
		this.n1 = n1;
		this.n2 = n2;
		this.sz = sz;
		this.c = Color.WHITE;
	}
	public double averageZ() {
		return ( n1.z + n2.z )/2;
	}
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(sz));
		g.setColor(c);
		g.drawLine((int) n1.x,(int) n1.y,(int) n2.x,(int) n2.y);
	}
	public void draw(Graphics g) {
		//g.setStroke(new BasicStroke(sz));
		g.setColor(c);
		g.drawLine((int) n1.x,(int) n1.y,(int) n2.x,(int) n2.y);
	}
	@Override
	void setColor(Color c) {
		// TODO Auto-generated method stub
		this.c = c;
	}
}
