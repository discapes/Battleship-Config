package com.clientfx.consolewindow;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.battleship.main.GameConfigMaker;
import server.battleship.main.MissileSiloConfigMaker;
import server.battleship.main.ShipsMapMaker;

public class ConsoleWindow
{
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
				while(!option.equalsIgnoreCase("q")) {
					ConsoleOutput.print("What would you like to make? (missile, game, ships) ");
					option = inputReader.getNextLine();
					ConsoleOutput.println(option);
					switch(option) {
					case "missile":
						MissileSiloConfigMaker.make(inputReader);
						break;
					case "game":
						GameConfigMaker.make(inputReader);
						break;
					case "ships":
						ShipsMapMaker.make(inputReader);
						break;
					}
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.start();
		return thread;
	}
}