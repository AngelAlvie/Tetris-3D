package orthoEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cube3D {
	public ZGetter[] stuff;
	public Node[] nodes;
	public int[] order;
	public double[] size;
	public double[] translation;
	public double[] COR;
	public Color cl;
	
	public Cube3D(double[] size, double[] translation,double[] COR, Color cl) {
		nodes = new Node[8];
		stuff = new ZGetter[18];
		order = new int[stuff.length];
		int c = 0;
		this.size = size;
		this.translation = translation;
		this.COR = COR;
		
		for (int i =0; i < 2; i++) {
			for (int j = 0; j< 2; j++) {
				for (int k = 0; k < 2; k++) {
					nodes[c] = new Node(i*size[0] - size[0]/2 + translation[0] - COR[0],j*size[1] - size[1]/2 + translation[1]- COR[1],	k*size[2] - size[2]/2+ translation[2]- COR[2],2);  //change this for the cubelets
					c++;
				}
			}
		}
		stuff[0] = new Edge(nodes[0],nodes[1],2);
		stuff[1] = new Edge(nodes[1],nodes[3],2);
		stuff[2] = new Edge(nodes[3],nodes[2],2);
		stuff[3] = new Edge(nodes[2],nodes[0],2);
		stuff[4] = new Edge(nodes[4],nodes[5],2);
		stuff[5] = new Edge(nodes[5],nodes[7],2);
		stuff[6] = new Edge(nodes[7],nodes[6],2);
		stuff[7] = new Edge(nodes[6],nodes[4],2);
		stuff[8] = new Edge(nodes[0],nodes[4],2);
		stuff[9] = new Edge(nodes[1],nodes[5],2);
		stuff[10] = new Edge(nodes[2],nodes[6],2);
		stuff[11] = new Edge(nodes[3],nodes[7],2);
		
		stuff[12] = new Face(new Node[]{nodes[0],nodes[1],nodes[3],nodes[2]},cl);
		stuff[13] = new Face(new Node[]{nodes[1],nodes[3],nodes[7],nodes[5]},cl);
		stuff[14] = new Face(new Node[]{nodes[0],nodes[1],nodes[5],nodes[4]},cl);
		stuff[15] = new Face(new Node[]{nodes[4],nodes[5],nodes[7],nodes[6]},cl);
		stuff[16] = new Face(new Node[]{nodes[0],nodes[2],nodes[6],nodes[4]},cl);
		stuff[17] = new Face(new Node[]{nodes[2],nodes[3],nodes[7],nodes[6]},cl);
		
		sortOrder();
	}
	
	public void setColor(Color c) {
		for (int i = 0; i < 6; i++) {
			stuff[i+12].setColor(c);
		}
	}
	
	public void rotateZ(double theta) {
		for (int i =0; i < nodes.length; i++) {
			Node node = nodes[i];
			double x = node.x;
			double y = node.y;
			node.x = x * Math.cos(theta) - y * Math.sin(theta);
			node.y = y * Math.cos(theta) + x * Math.sin(theta);
		}
	}
	public void rotateY(double theta) {
		for (int i =0; i < nodes.length; i++) {
			Node node = nodes[i];
			double x = node.x;
			double z = node.z;
			node.x = x * Math.cos(theta) - z * Math.sin(theta);
			node.z = z * Math.cos(theta) + x * Math.sin(theta);
		}
	}
	public void rotateX(double theta) {
		for (int i =0; i < nodes.length; i++) {
			Node node = nodes[i];
			double y = node.y;
			double z = node.z;
			node.y = y * Math.cos(theta) - z * Math.sin(theta);
			node.z = z * Math.cos(theta) + y * Math.sin(theta);
		}
	}
	
	public void sortOrder() {
		
		double[] k = new double[stuff.length];
		
		for (int i = 0; i < stuff.length; i++) {
			order[i] =i;
			k[i] = stuff[i].averageZ();	
			
		}
		int t;
		double t2;
		for (int i = 0; i < stuff.length-1; i++) {
			for (int j = 0; j < stuff.length-1; j++) {
				if(k[j] < k[j+1]) {
					t2 = k[j];
					t = order[j];
					
					order[j] = order[j+1];
					k[j] = k[j+1];
					
					order[j+1] = t;
					k[j+1] = t2;
				}
			}
		}
	}
	
	//overwrite this in the container draw method.
	
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate((int)COR[0],(int)COR[1]);
		for (int i =0; i < stuff.length; i++) {
			stuff[order[i]].draw(g2);
		}
		g2.translate((int)-COR[0],(int)-COR[1]);
	}
	
}
