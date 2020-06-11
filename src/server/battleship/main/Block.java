package server.battleship.main;

import java.io.Serializable;

class Block implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	private boolean hasBeenHit;
	
	Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.hasBeenHit = false;
	}
	
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	
	void hit() {
		hasBeenHit = true;
	}
	
	boolean getHasBeenHit() {
		return hasBeenHit;
	}
}
