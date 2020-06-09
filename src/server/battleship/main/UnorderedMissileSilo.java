package server.battleship.main;

import java.io.Serializable;

class UnorderedMissileSilo extends MissileSilo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int[] missileCounts;
	Missile currentMissile;
	
	UnorderedMissileSilo(int[] missileCounts) {
		missileSiloType = "Unordered";
		this.missileCounts = missileCounts;
		this.currentMissile = Missile.SINGLE;
	}
	
	@Override
	public String getCurrentMissileName() {
		return currentMissile.getName();
	}
	
	@Override
	public int getNumOfMissile(int missileType) {
		try {
		return missileCounts[missileType];
		} catch (IndexOutOfBoundsException e) {};
		return -1;
	}
	
	@Override
	public void setCurrentMissile(int missileType) {
		try {
		currentMissile = Missile.values()[missileType];
		} catch (IndexOutOfBoundsException e) {};
	}
	
	@Override
	Missile fireCurrentMissile() {
		if (missileCounts[currentMissile.getValue()] == 0) {
			return null;
		} else {
			missileCounts[currentMissile.getValue()]--;
			return currentMissile;
		}
	}
	
}