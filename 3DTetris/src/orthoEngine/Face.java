package orthoEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Face extends ZGetter{

	Node[] n;
	public Color c;
	public Face(Node[] n, Color c) {
		this.n = n;
		this.c = c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public double averageZ() {
		double tmp = 0;
		for (int i = 0; i < n.length;i++) {
			tmp += n[i].z;
		}
		tmp /= n.length;
		return tmp;
	}
	
	public void draw(Graphics2D g){
		Polygon p = new Polygon();
		for (int i = 0; i < n.length; i++) {
			p.addPoint((int) n[i].x, (int) n[i].y);
		}
		g.setColor(c);
		g.fill(p);
	}
	public void draw(Graphics g){
		Polygon p = new Polygon();
		for (int i = 0; i < n.length; i++) {
			p.addPoint((int) n[i].x, (int) n[i].y);
		}
		g.setColor(c);
		g.drawPolygon(p);
	}
	
	
}
