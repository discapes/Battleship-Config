package server.battleship.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MissileSiloConfigMaker
{
	public static void make(Scanner scanner) {
		MissileSilo m = null;
		
		String siloType = "neither";
		while (siloType.equals("neither")) {
			System.out.println("Do you want an 'ordered' or 'unordered' Missile Silo?");
			switch (scanner.nextLine()) {
			case "ordered":
				siloType = "ordered";
				break;
			case "unordered":
				siloType = "unordered";
				break;
			}
		}
		System.out.println("You chose " + siloType);

		if (siloType.equals("unordered")) {
			int[] missileCounts = new int[Missile.values().length];
			for (int i = 0; i < Missile.values().length; i++) {

				int option = -1;
				while (option == -1) {
					System.out.println("How many " + Missile.values()[i] + " missiles do you want?");
					try { option = scanner.nextInt(); }
					catch (InputMismatchException e) {}
					missileCounts[i] = option;
				}
			}
			m = new UnorderedMissileSilo(missileCounts);
		} else if (siloType.equals("ordered")) {
			ArrayList<Missile> missiles = new ArrayList<Missile>();
			String option = "";
			System.out.println("Add missiles to the silo in order of usage. (EOF to save) (v_LiNe, H_line, single, SPLASH");
			while (!option.equals("EOF")) {
				System.out.print(missiles.size() + " ");
				try { option = scanner.nextLine(); }
				catch (InputMismatchException e) {}
				try { missiles.add(Missile.valueOf(option.toUpperCase())); } 
				catch (IllegalArgumentException e) {};
			}
			m = new OrderedMissileSilo(missiles);
			
		} else { System.out.println("How did we get here?"); }

		scanner.close();
		

		try {
			FileOutputStream fileOut = new FileOutputStream("missilesilo.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(m);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in missilesilo.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
