package com.clientfx.confirmbox;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox
{

	private Stage stage;
	boolean answer;
	private ConfirmBoxController controller;

	public ConfirmBox() throws IOException
	{
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		FXMLLoader loader = new FXMLLoader(ConfirmBox.class.getResource("/com/clientfx/confirmbox/ConfirmBox.fxml"));
		stage.setScene(new Scene((AnchorPane) loader.load()));
		controller = loader.<ConfirmBoxController>getController();
	}

	public boolean ask(String title, String question) throws IOException
	{
		stage.setTitle(title);
		controller.initialize(this, question, stage);
		stage.showAndWait();
		return answer;
	}

}
