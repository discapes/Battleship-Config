package server.battleship.main;

import java.io.IOException;

import com.clientfx.confirmbox.ConfirmBox;
import com.clientfx.consolewindow.ConsoleController;
import com.clientfx.consolewindow.ConsoleWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class ConfigMaker extends Application
{
	private Thread consoleCfgThread;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Battleships config maker");
		stage.setOnCloseRequest(e -> {
			e.consume();
			try
			{
				ConfirmBox confirmBox = new ConfirmBox();
				if (confirmBox.ask("Exit?", "Are you sure you want to exit?"))
					stage.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		});
		ConsoleWindow consoleWindow = new ConsoleWindow();
		// TODO make daemon
		consoleCfgThread = consoleWindow.startConsoleCfg(stage);
		stage.show();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		consoleCfgThread.stop();
	}
}
