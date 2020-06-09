package server.battleship.main;

import java.io.Serializable;

public class GameConfig implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int cols;
	private int rows;
	int getCols() { return cols; }
	int getRows() { return rows; }
	GameConfig(int cols, int rows)
	{
		this.cols = cols;
		this.rows = rows;
	}

}
