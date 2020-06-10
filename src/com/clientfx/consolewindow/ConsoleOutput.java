package com.clientfx.consolewindow;

import javafx.scene.control.TextArea;

public class ConsoleOutput
{
	private static final int limit = 1000;
	private static ConsoleOutput instance;
	private static TextArea console;
	public static ConsoleOutput get() { return instance; }
	public static void setConsole(TextArea console) { ConsoleOutput.console = console; }
	
	private static void limit() {
		if (console.getLength() > limit) {
			console.replaceText(0, console.getLength()-limit, "");
		}
	}
	
	public static void print(String str) {
		console.appendText(str);
		limit();
	}
	
	public static void println(String str) {
		console.appendText(str + "\n");
		limit();
	}
	
	public static void print(int i) {
		console.appendText(Integer.toString(i));
		limit();
	}
	
	public static void println(int i) {
		console.appendText(Integer.toString(i) + "\n");
		limit();
	}
}
