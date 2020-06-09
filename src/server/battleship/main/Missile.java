package server.battleship.main;

enum Missile {
	V_LINE(0, "V-Line"),
	H_LINE(1, "H-Line"),
	SINGLE(2, "Single"),
	SPLASH(3, "Splash");

	private int value;
	private String name;

	private Missile(int value, String name) {
		this.value = value;
		this.name = name;
	}

	int getValue() {
		return value;
	}

	String getName() {
		return name;
	}
}

