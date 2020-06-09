package server.battleship.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class GameConfigMaker
{
	public static void make(Scanner scanner) {
		System.out.println("No. of columns?");
		int cols = scanner.nextInt();
		System.out.println("No. of rows?");
		int rows = scanner.nextInt();
		GameConfig gameConfig = new GameConfig(cols, rows);
		try {
			FileOutputStream fileOut = new FileOutputStream("gameconfig.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameConfig);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in gameconfig.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
