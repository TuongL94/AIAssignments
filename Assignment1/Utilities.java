
public class Utilities {
	
	public static String translateNbrToLetter(int nbr) {
		String letter = "";
		switch(nbr) {
			case 0:
				letter = "a";
				break;
			case 1:
				letter = "b";
				break;
			case 2:
				letter = "c";
				break;
			case 3:
				letter = "d";
				break;
			case 4:
				letter = "e";
				break;
			case 5:
				letter = "f";
				break;
			case 6:
				letter = "g";
				break;
			case 7:
				letter = "h";
				break;
			default:
				break;
		}
		return letter;
	}
	
	public static int translateLetterToNbr(String letter) {
		int nbr = -1;
		switch(letter) {
			case "a":
				nbr = 0;
				break;
			case "b":
				nbr = 1;
				break;
			case "c":
				nbr = 2;
				break;
			case "d":
				nbr = 3;
				break;
			case "e":
				nbr = 4;
				break;
			case "f":
				nbr = 5;
				break;
			case "g":
				nbr = 6;
				break;
			case "h":
				nbr = 7;
				break;
			default:
				break;
		}
		return nbr;
	}
}
