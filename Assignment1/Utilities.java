
public class Utilities {
	
	/**
	 * Converts the numbers 0-7 to letters corresponding to the 1st-8th letters in the alphabet
	 * @param nbr - the number
	 * @return the corresponding letter
	 */
	public static String nbrToLetter(int nbr) {
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
	
	/**
	 * Converts the 1st-8th letters in the alphabet to the corresponding numbers 0-7.
	 * @param letter - the letter
	 * @return the corresponding number
	 */
	public static int letterToNbr(String letter) {
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
