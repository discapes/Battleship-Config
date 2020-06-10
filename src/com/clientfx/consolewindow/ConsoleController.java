package com.clientfx.consolewindow;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ConsoleController
{

	@FXML private TextArea textarea;
	@FXML private TextField textfield;

	public void initialize()
	{
		InputReader inputReader = ConsoleWindow.getInputReader();
		ConsoleOutput.setConsole(textarea);
		textfield.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	                inputReader.append(textfield.getText());
	                textfield.clear();
	            }
	        }
	    });
	}
}
