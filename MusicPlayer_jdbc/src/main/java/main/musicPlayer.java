package main;

import java.util.Scanner;

import songOperation.Operation;

public class musicPlayer {
	
	private static void musicApp() throws Exception
	{

		System.out.println("1. Add Song\n"
				+ "2. Play Song\n"
				+ "3. Edit Song\n"
				+ "4. View All Song\n");
		
		Scanner sc = new Scanner(System.in);
		
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			Operation.addSong();
			break;
		case 2:
			Operation.playSong();
			System.out.println(" is playing.........");
			break;
		case 3:
			Operation.editSong();
			break;
		case 4:
			Operation.viewAllSong();
			break;

		default:
			System.out.println("Please Select correct option");
			System.out.println();
			musicApp();
			break;
		}
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		musicApp();

	}

}
