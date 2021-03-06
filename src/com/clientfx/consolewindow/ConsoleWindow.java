package com.clientfx.consolewindow;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.battleship.main.GameConfigMaker;
import server.battleship.main.MissileSiloConfigMaker;
import server.battleship.main.ShipsMapMaker;

public class ConsoleWindow
{
	public static String ask(String question) {
		ConsoleOutput.print(question + " ");
		String input = inputReader.getNextLine();
		ConsoleOutput.println(input);
		return input;
	}

	public static int askInt(String question) {
		ConsoleOutput.print(question + " ");
		int input = inputReader.nextInt();
		ConsoleOutput.println(input);
		return input;
	}

	private Scene scene;
	private static InputReader inputReader;
	public static InputReader getInputReader() {
		return inputReader;
	}

	public ConsoleWindow() throws IOException
	{
		inputReader = new InputReader();
		FXMLLoader loader = new FXMLLoader(ConsoleWindow.class.getResource("/com/clientfx/consolewindow/Console.fxml"));
		VBox root = loader.load();
		scene = new Scene(root);
	}

	public Thread startConsoleCfg(Stage stage) throws IOException
	{
		stage.setScene(scene);

		Task<Void> task = new Task<Void>() {
			@Override public Void call() {
				String option = "";
				while(true) {
					option = ask("What would you like to make? (missile, game, ships) (q to quit)");
					switch(option) {
					case "missile":
						MissileSiloConfigMaker.make(inputReader);
						break;
					case "game":
						GameConfigMaker.make(inputReader);
						break;
					case "ships":
						int cols = askInt("Columns?");
						int rows = askInt("Rows?");
						Platform.runLater(() -> ShipsMapMaker.make(inputReader, stage, cols, rows));
						return null;
					}
					if (option.equalsIgnoreCase("q")) {
						Platform.runLater(() -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
					}
				}
			}
		};
		Thread thread = new Thread(task);
		thread.start();
		return thread;
	}
}