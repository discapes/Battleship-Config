package server.battleship.main;

import java.io.Serializable;
import java.util.ArrayList;

class OrderedMissileSilo extends MissileSilo implements Serializable {

	private static final long serialVersionUID = 1L;


	ArrayList<Missile> missiles;

	OrderedMissileSilo(ArrayList<Missile> missiles) {
		missileSiloType = "Ordered";
		this.missiles = missiles;
	}

	@Override
	public String getCurrentMissileName() {
		return getMissileNameAt(0);
	}

	@Override
	Missile fireCurrentMissile() {
		if (missiles.size() == 0) {
			return null;
		} else {
			return missiles.remove(0);
		}
	}

	@Override
	public String getMissileNameAt(int index) {
			try {
				return missiles.get(index).getName();
			} catch (IndexOutOfBoundsException e) {
				return "";
			}
	}

	@Override
	public int getTotalNumOfMissiles() {
		return missiles.size();
	}
}
