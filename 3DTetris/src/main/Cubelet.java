package main;

import java.awt.Color;
import java.awt.Graphics;

import orthoEngine.Cube3D;

public class Cubelet extends Cube3D{
	public boolean isOccupied;
	
	public Cubelet(boolean b,double[] size, double[] pos, double[] COR, Color c) {
		super(size,pos,COR,c);
		isOccupied = b;
	}
	
	public void render(Graphics g){
		if (isOccupied) {
			draw(g);
		}
	}
	
}
