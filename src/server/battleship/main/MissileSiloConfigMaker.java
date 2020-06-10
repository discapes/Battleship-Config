package server.battleship.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

import com.clientfx.consolewindow.ConsoleOutput;
import com.clientfx.consolewindow.InputReader;

public class MissileSiloConfigMaker
{
	public static void make(InputReader inputReader) {
		MissileSilo m = null;
		
		String siloType = "neither";
		while (siloType.equals("neither")) {
			ConsoleOutput.println("Do you want an 'ordered' or 'unordered' Missile Silo?");
			switch (inputReader.getNextLine()) {
			case "ordered":
				siloType = "ordered";
				break;
			case "unordered":
				siloType = "unordered";
				break;
			}
		}
		ConsoleOutput.println("You chose " + siloType);

		if (siloType.equals("unordered")) {
			int[] missileCounts = new int[Missile.values().length];
			for (int i = 0; i < Missile.values().length; i++) {

				int option = -1;
				while (option == -1) {
					ConsoleOutput.println("How many " + Missile.values()[i] + " missiles do you want?");
					try { option = inputReader.nextInt(); }
					catch (InputMismatchException e) {}
					missileCounts[i] = option;
				}
			}
			m = new UnorderedMissileSilo(missileCounts);
		} else if (siloType.equals("ordered")) {
			ArrayList<Missile> missiles = new ArrayList<Missile>();
			String option = "";
			ConsoleOutput.println("Add missiles to the silo in order of usage. (EOF to save) (v_LiNe, H_line, single, SPLASH");
			while (!option.equals("EOF")) {
				ConsoleOutput.print(missiles.size() + " ");
				try { option = inputReader.getNextLine(); }
				catch (InputMismatchException e) {}
				try { missiles.add(Missile.valueOf(option.toUpperCase())); } 
				catch (IllegalArgumentException e) {};
			}
			m = new OrderedMissileSilo(missiles);
			
		} else { ConsoleOutput.println("How did we get here?"); }
		

		try {
			FileOutputStream fileOut = new FileOutputStream("missilesilo.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m);
			out.close();
			fileOut.close();
			ConsoleOutput.println("Serialized data is saved in missilesilo.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
