package com.clientfx;

import java.util.concurrent.Callable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class AskBox implements Callable<String>
{
	final Window owner;
	final String question;

	public AskBox(Window owner, String question) {
		this.question = question;
		this.owner = owner;
	}

	@Override
	public String call() throws Exception
	{
		final Stage dialog = new Stage();
		dialog.setTitle(question);
		dialog.initOwner(owner);
		dialog.initStyle(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);

		final TextField textField = new TextField();
		final Button submitButton = new Button("Submit");
		submitButton.setDefaultButton(true);
		submitButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent t)
			{
				dialog.close();
			}
		});

		final VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER_RIGHT);
		layout.setStyle("-fx-padding: 10;");
		layout.getChildren().setAll(textField, submitButton);

		dialog.setScene(new Scene(layout));
		dialog.showAndWait();

		return textField.getText();
	}
}
