package com.clientfx.consolewindow;

import java.util.ArrayList;

public class InputReader
{
	private ArrayList<String> lines;
	
	public InputReader()
	{
		lines = new ArrayList<String>();
	}
	
	public String getNextLine() {
		while (lines.size() == 0) {
			try
			{
				Thread.sleep(2);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
		return lines.remove(0);
	}
	
	public void append(String str) {
		lines.add(str);
	}
	
	public int nextInt() {
		while (true) {
			try {
				int i = Integer.parseInt(getNextLine());
				return i;
			} catch (NumberFormatException e) {}
		}
	}
}
