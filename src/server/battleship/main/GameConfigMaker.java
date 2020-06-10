package server.battleship.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.clientfx.consolewindow.ConsoleOutput;
import com.clientfx.consolewindow.InputReader;

public class GameConfigMaker
{
	public static void make(InputReader inputReader) {
		ConsoleOutput.print("No. of columns? ");
		int cols = inputReader.nextInt();
		ConsoleOutput.println(cols);
		ConsoleOutput.print("No. of rows? ");
		int rows = inputReader.nextInt();
		ConsoleOutput.println(rows);
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
