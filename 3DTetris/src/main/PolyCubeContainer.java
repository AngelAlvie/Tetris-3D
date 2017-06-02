package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import orthoEngine.Cube3D;
import orthoEngine.ZGetter;

public class PolyCubeContainer extends Cube3D implements KeyListener {
	
	Cubelet[][][] voxels;									//this is the 3d array of cublet data which will be contain threed shapes in themselves
	ArrayList<ZGetter> drawableShapes;
	PolyCube tempPolyCube;									// cubey is the exterior canister
	double xTilt;
	boolean CW, CCW;
	double tiltSpeed = Math.PI/100;
	int[] ultimateSortOrder;
	
	
	public PolyCubeContainer() {
		super(new double[] {200,480,200},new double[] {Tetris.WIDTH/2, Tetris.HEIGHT/2,0},new double[] {Tetris.WIDTH/2, Tetris.HEIGHT/2,0},new Color(0,0,0,0));				//instantize the container
		voxels = new Cubelet[10][24][10];
		
		xTilt = Math.PI/15;
		rotateX(xTilt); 									//set default tilt toward front of the screen
															//could extend the Cube3D object to directly take the ZGetters of everything
		CW = false;											//set all the defaults
		CCW = false;

		for (int i = 0; i < voxels.length; i++) {
			for (int j = 0; j < voxels[0].length; j++) {
				for (int k = 0; k < voxels[0][0].length; k++) {	
					voxels[i][j][k] = new Cubelet(true, new double[] {20,20,20},new double[]{i*20+Tetris.WIDTH/2 - 90,j*20+Tetris.HEIGHT/2 - 230,k*20 - 90},new double[] {Tetris.WIDTH/2, Tetris.HEIGHT/2,0}, Color.BLUE);
					voxels[i][j][k].rotateX(xTilt); 
					voxels[i][j][k].sortOrder();
				}
			}
		}
		
		drawableShapes = new ArrayList<ZGetter>();
		
		for (int i = 0; i < this.stuff.length; i++) {
			drawableShapes.add(this.stuff[i]);
		}
		for (int i = 0; i < voxels.length; i++) {
			for (int j = 0; j < voxels[0].length; j++) {
				for (int k = 0; k < voxels[0][0].length; k++) {	
					for (int l = 0; l < voxels[0][0][0].stuff.length; l++) {
						drawableShapes.add(voxels[i][j][k].stuff[i]);
					}
				}
			}
		}
		ultimateSortOrder = new int[drawableShapes.size()];
		
	}
	
	public void newWorldOrder() {
		double[] k = new double[drawableShapes.size()];
		
		for (int i = 0; i < drawableShapes.size(); i++) {
			ultimateSortOrder[i] =i;
			k[i] = drawableShapes.get(i).averageZ();	
			
		}
		
		int t;														//because bubble sort is HORRIBLE
		double t2;
		for (int i = 1; i < drawableShapes.size()-1; i++) {
			for (int j = i; j > 0; j--) {
				if (k[j] < k[j+1]) {
					t2 = k[j];
					t = ultimateSortOrder[j];
					
					ultimateSortOrder[j] = ultimateSortOrder[j+1];
					k[j] = k[j+1];
					
					ultimateSortOrder[j+1] = t;
					k[j+1] = t2;
				}
			}
		}
		
		
		/*int t;														//because bubble sort is HORRIBLE
		double t2;
		for (int i = 0; i < drawableShapes.size()-1; i++) {
			for (int j = 0; j < drawableShapes.size()-1; j++) {
				if(k[j] < k[j+1]) {
					t2 = k[j];
					t = ultimateSortOrder[j];
					
					ultimateSortOrder[j] = ultimateSortOrder[j+1];
					k[j] = k[j+1];
					
					ultimateSortOrder[j+1] = t;
					k[j+1] = t2;
				}
			}
		}*/
	}
	
	public void render(Graphics g) {
		newWorldOrder();
			if (CW) {											//could extend the Cube3D object to directly take the ZGetters of everything
			rotateY(-Math.cos(xTilt)*tiltSpeed);		
			rotateZ(Math.sin(xTilt)*tiltSpeed);	

			for (int i = 0; i < voxels.length; i++) {
				for (int j = 0; j < voxels[0].length; j++) {
					for (int k = 0; k < voxels[0][0].length; k++) {
						voxels[i][j][k].rotateY(-Math.cos(xTilt)*tiltSpeed);	
						voxels[i][j][k].rotateZ(Math.sin(xTilt)*tiltSpeed);
					}
				}
			}												//set of operations which effectively tilt the axis of rotation about the x axis
		} else if (CCW) {
			rotateY(Math.cos(xTilt)*tiltSpeed);					//inverse of these operations
			rotateZ(-Math.sin(xTilt)*tiltSpeed);
			for (int i = 0; i < voxels.length; i++) {
				for (int j = 0; j < voxels[0].length; j++) {
					for (int k = 0; k < voxels[0][0].length; k++) {
						voxels[i][j][k].rotateY(Math.cos(xTilt)*tiltSpeed);	
						voxels[i][j][k].rotateZ(-Math.sin(xTilt)*tiltSpeed);
					}
				}
			}
		}
	
		for (int i = 0; i < drawableShapes.size(); i++) {
			drawableShapes.get(i).draw(g);

		}	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {					//this sets booleans which keep track of when a key is pressed for CW and CCW
		if (e.getKeyCode() == KeyEvent.VK_A) {
			CW = true;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			CCW = true;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() != KeyEvent.VK_D) {
			CW = false;
		}
		
		if (e.getKeyCode() != KeyEvent.VK_A) {
			CCW = false;
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
