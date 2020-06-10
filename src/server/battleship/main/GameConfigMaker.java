package server.battleship.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clientfx.consolewindow.ConsoleOutput;
import com.clientfx.consolewindow.ConsoleWindow;
import com.clientfx.consolewindow.InputReader;

public class GameConfigMaker
{
	public static void make(InputReader inputReader) {
		int cols = ConsoleWindow.askInt("No. of columns? ");
		int rows = ConsoleWindow.askInt("No. of rows? ");
		GameConfig gameConfig = new GameConfig(cols, rows);
		try {
			FileOutputStream fileOut = new FileOutputStream("gameconfig.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameConfig);
			out.close();
			fileOut.close();
			ConsoleOutput.println("Serialized data is saved in gameconfig.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
