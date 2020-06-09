package server.battleship.main;

import java.io.Serializable;

abstract class MissileSilo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String missileSiloType;
	
	abstract Missile fireCurrentMissile();
	abstract String getCurrentMissileName();
	String getMissileSiloType() { return missileSiloType; };
	
	String getMissileNameAt(int index) { return ""; }
	int getNumOfMissile(int missileType) { return 0; }
	int getTotalNumOfMissiles() { return 0; }
	void setCurrentMissile(int missileType) {}
}
