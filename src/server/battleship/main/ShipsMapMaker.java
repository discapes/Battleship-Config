package server.battleship.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.clientfx.AskBox;
import com.clientfx.consolewindow.ConsoleOutput;
import com.clientfx.consolewindow.ConsoleWindow;
import com.clientfx.consolewindow.InputReader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ShipsMapMaker
{

	private static GridPane grid;
	private static Button[][] buttons;
	private static int numShips;
	private static HashMap<String, HashSet<Block>> ships;
	private static Stage stage;
	private static boolean isDone;
	
	private static String currentName;
	private static HashSet<Block> currentBlocks;
	private static int currentLength;
	
	private static String askString(String question, Stage stage) {
		AskBox askBox = new AskBox(stage, question);
		String result = "error";
		try {
			result = askBox.call();
		} catch (Exception e) {	e.printStackTrace(); }
		return result;
	}
	
	public static void make(InputReader inputReader, Stage stage, int cols, int rows)
	{
		isDone = false;
		ships = new HashMap<String, HashSet<Block>>();
		grid = new GridPane();
		buttons = new Button[cols][rows];
		initGrids(cols, rows);
		ShipsMapMaker.stage = stage;
		Platform.runLater(() -> stage.setHeight(800));
		Platform.runLater(() -> stage.setWidth(800));
		Platform.runLater(() -> stage.setScene(new Scene(grid)));
        numShips = Integer.parseInt(askString("How many ships?", stage));
        currentName = askString("First ship name?", stage);
        currentLength = Integer.parseInt(askString("Length?", stage));
        currentBlocks = new HashSet<Block>();
	}
	
	private static void write(Stage stage) {
		ConsoleWindow consoleWindow = null;
		try{
			consoleWindow = new ConsoleWindow();
		} catch (IOException e1){e1.printStackTrace();}
		try {
			FileOutputStream fileOut = new FileOutputStream("ships.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(ships);
			out.close();
			fileOut.close();
			ConsoleOutput.println("Serialized data is saved in ships.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
		try{
			consoleWindow.startConsoleCfg(stage);
		} catch (IOException e){e.printStackTrace();}

	}

	private static void initGrids(int cols, int rows)
	{
		buttons = new Button[cols][rows];
		for (int i = 0; i < cols; i++)
		{
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			grid.getColumnConstraints().add(cc);
		}
		for (int i = 0; i < rows; i++)
		{
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			grid.getRowConstraints().add(rc);
		}
		for (int x = 0; x < cols; x++)
		{
			for (int y = 0; y < rows; y++)
			{
				Button button = new Button();
				GridPane.setConstraints(button, x, y);
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				button.setPadding(Insets.EMPTY);
				button.setMinSize(0.f, 0.f);
				grid.getChildren().add(button);
				buttons[x][y] = button;
				button.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0)
					{
						if (isDone) return;
						int x = GridPane.getColumnIndex(button);
						int y = GridPane.getRowIndex(button);
						button.setDisable(true);
						button.setStyle("-fx-background-color: grey;");
						currentBlocks.add(new Block(x,y));
						if (currentBlocks.size() == currentLength) {
							ships.put(currentName, currentBlocks);
							if (ships.size() == numShips) {
								write(stage);
								return;
							}

					        currentName = askString("Next ship name?", stage);
					        currentLength = Integer.parseInt(askString("Next ship length?", stage));
					        currentBlocks = new HashSet<Block>();
						}
					}
				});
			}
		}
	}
}
