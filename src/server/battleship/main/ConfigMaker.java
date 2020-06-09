package server.battleship.main;

import java.util.Scanner;

public class ConfigMaker
{

	public static void main(String[] args)
	{
		String option = "";
		Scanner scanner = new Scanner(System.in);
		while(!option.equalsIgnoreCase("q")) {
			System.out.println("What would you like to make? (missile, game, ships)");
			option = scanner.nextLine();
			switch(option) {
			case "missile":
				MissileSiloConfigMaker.make(scanner);
				break;
			case "game":
				GameConfigMaker.make(scanner);
				break;
			case "ships":
				ShipsMapMaker.make(scanner);
				break;
			}
		}
		

	}
}
