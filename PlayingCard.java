
/**
 * @author Emma Dreist
 * 
 * Assignment 1: Aces up
 * Date: 2-2-17
 * Worked with: Lauren, Magnus, Amanda, and Kalia 
 * 
 */

/**
 * 
 * @author emadreist
 *
 *         This class creates a new card, it starts by declaring variables for
 *         the card itself, an integer of the card rank, and a character of the card suit.
 */

public class PlayingCard implements Comparable<PlayingCard> {
	private PlayingCard card1;
	private int cardRank;
	private char cardSuit;

	/**
	 * This method takes the variables declared in the previous method, and sets
	 * them equal to the parameters that are being passed in.
	 * 
	 * @param integer:
	 *            cardRank
	 * @param integer:
	 *            cardSuit
	 */
	public PlayingCard(int cardRank, char cardSuit) {
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}

	/**
	 * This method does not take in any parameters, it simply just returns the
	 * rank of the card
	 * 
	 * @return integer of card rank (1-13)
	 */
	public int getRank() {
		return cardRank;
	}

	/**
	 * This method also takes in no parameters, and just returns the suit of the
	 * card
	 * 
	 * @return character of card suit (H, S, C, D)
	 */
	public char getSuit() {
		return cardSuit;
	}

	/**
	 * 
	 * This method compares the ranks of the card declared in the first method
	 * to the card passed as a parameter. The method then checks if the first
	 * card has a lower rank than the parameter card. If so it returns a
	 * positive number, but if the first card has a greater rank than the
	 * parameter card, it returns a negative number. If both of these are false
	 * it returns a 0.
	 * 
	 * @param PlayingCard:
	 *            card2
	 * @return Integer that is either positive, negative, or zero (1, -1, 0)
	 */
	public int compareTo(PlayingCard card2) {
		if (card1.getRank() < card2.getRank()) {
			return 1;
		}
		if (card1.getRank() > card2.getRank()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * This method takes the card rank and the card suit, and strings them
	 * together adding the suffix ".png" to each pairing to create the file name
	 * of the image of each card.
	 * 
	 * @return String of the card rank, card suit, and the suffix ".png"
	 */
	public String getImageFileName() {
		return cardRank + "" + cardSuit + ".png";
	}

	/**
	 * 
	 * This method returns a string that declares the card's rank and suit.
	 * 
	 * @return String that states the card rank and the card suit.
	 */
	public String toString() {
		return cardRank + "" + cardSuit;
	}

}
