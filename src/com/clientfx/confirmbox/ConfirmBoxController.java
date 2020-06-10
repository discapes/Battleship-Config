package com.clientfx.confirmbox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmBoxController
{

	@FXML
	private Label confirmLabel;
	private ConfirmBox confirmBox;
	private Stage stage;
	
	public void confirmNoClicked()
	{
		confirmBox.answer = false;
		stage.close();
	}

	public void confirmYesClicked()
	{
		confirmBox.answer = true;
		stage.close();
	}

	public void initialize(ConfirmBox confirmBox, String question, Stage stage)
	{
		this.confirmBox = confirmBox;
		this.confirmLabel.setText(question);
		this.stage = stage;
	}
}
